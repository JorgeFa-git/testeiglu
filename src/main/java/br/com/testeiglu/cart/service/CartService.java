package br.com.testeiglu.cart.service;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.cart.domain.Cart;
import br.com.testeiglu.core.utils.SessionUtils;
import br.com.testeiglu.discount.domain.DiscountEnum;
import br.com.testeiglu.discount.service.DiscountService;
import br.com.testeiglu.ingredient.domain.Ingredient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final DiscountService discountService;

    public CartService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public BigDecimal calculateTotal(List<Ingredient> ingredients) {
        var total = BigDecimal.ZERO;
        for (var ingredient : ingredients) {
            total = total.add(ingredient.getPrice());
        }
        return total;
    }

    public Cart getCartFromSession(HttpSession session) {
        List<Burger> burgersFromSession = SessionUtils.getBurgersFromSession(session);
        var total = this.calculateTotal(this.getAllIngredientsFromBurgerList(burgersFromSession));
        var totalDiscount = this.discountService.calculateTotalDiscount(burgersFromSession);
        return new Cart(burgersFromSession, totalDiscount, total.subtract(totalDiscount));
    }

    public List<Ingredient> getAllIngredientsFromBurgerList(List<Burger> burgers) {
        List<Ingredient> list = new ArrayList<>();
        for (Burger burger : burgers) {
            list.addAll(burger.getIngredients());
        }
        return list;
    }
}
