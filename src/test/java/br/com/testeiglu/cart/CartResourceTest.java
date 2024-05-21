package br.com.testeiglu.cart;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.cart.domain.Cart;
import br.com.testeiglu.discount.domain.DiscountEnum;
import br.com.testeiglu.discount.service.DiscountService;
import br.com.testeiglu.menu.service.dto.BurgerEdition;
import br.com.testeiglu.util.JsonHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DiscountService discountService;

    @Test
    @Transactional
    void shouldAdd_LIGHT_DiscountToBurger() throws Exception {
        var mockHttpSession = new MockHttpSession();

        BurgerEdition edition = new BurgerEdition();
        edition.setIngredientIds(List.of(100001L, 100003L));

        var result = this.mockMvc.perform(post("/api/burger/personalized")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(edition))
                .session(mockHttpSession)
            ).andExpect(status().isCreated()).andReturn();

        var response = result.getResponse();

        Burger burger = JsonHelper.toObject(response.getContentAsByteArray(), Burger.class);

        assertThat(burger).isNotNull();
        assertThat(burger.getDiscounts()).contains(DiscountEnum.LIGHT);

        response = this.mockMvc.perform(get("/api/cart").session(mockHttpSession)).andExpect(status().isOk())
            .andReturn().getResponse();

        Cart cart = JsonHelper.toObject(response.getContentAsByteArray(), Cart.class);

        var expectedDiscount = discountService.calculateTotalDiscount((List<Burger>) mockHttpSession.getAttribute("burgers"));

        assertThat(cart).isNotNull();
        assertThat(cart.getDiscount()).isEqualTo(expectedDiscount);
    }

    @Test
    @Transactional
    void shouldAdd_MUITA_CARNE_DiscountToBurger() throws Exception {
        var mockHttpSession = new MockHttpSession();

        BurgerEdition edition = new BurgerEdition();
        edition.setIngredientIds(List.of(100003L, 100003L, 100003L));

        var result = this.mockMvc.perform(post("/api/burger/personalized")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonHelper.toJson(edition))
            .session(mockHttpSession)
        ).andExpect(status().isCreated()).andReturn();

        var response = result.getResponse();

        Burger burger = JsonHelper.toObject(response.getContentAsByteArray(), Burger.class);

        assertThat(burger).isNotNull();
        assertThat(burger.getDiscounts()).contains(DiscountEnum.MUITA_CARNE);

        response = this.mockMvc.perform(get("/api/cart").session(mockHttpSession)).andExpect(status().isOk())
            .andReturn().getResponse();

        Cart cart = JsonHelper.toObject(response.getContentAsByteArray(), Cart.class);

        var expectedDiscount = discountService.calculateTotalDiscount((List<Burger>) mockHttpSession.getAttribute("burgers"));

        assertThat(cart).isNotNull();
        assertThat(cart.getDiscount()).isEqualTo(expectedDiscount);
    }

    @Test
    @Transactional
    void shouldAdd_MUIT_QUEIJO_DiscountToBurger() throws Exception {
        var mockHttpSession = new MockHttpSession();

        BurgerEdition edition = new BurgerEdition();
        edition.setIngredientIds(List.of(100005L, 100005L, 100005L));

        var result = this.mockMvc.perform(post("/api/burger/personalized")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonHelper.toJson(edition))
            .session(mockHttpSession)
        ).andExpect(status().isCreated()).andReturn();

        var response = result.getResponse();

        Burger burger = JsonHelper.toObject(response.getContentAsByteArray(), Burger.class);

        assertThat(burger).isNotNull();
        assertThat(burger.getDiscounts()).contains(DiscountEnum.MUITO_QUEIJO);

        response = this.mockMvc.perform(get("/api/cart").session(mockHttpSession)).andExpect(status().isOk())
            .andReturn().getResponse();

        Cart cart = JsonHelper.toObject(response.getContentAsByteArray(), Cart.class);

        var expectedDiscount = discountService.calculateTotalDiscount((List<Burger>) mockHttpSession.getAttribute("burgers"));

        assertThat(cart).isNotNull();
        assertThat(cart.getDiscount()).isEqualTo(expectedDiscount);
    }
}
