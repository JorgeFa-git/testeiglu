package br.com.testeiglu.menu;

import br.com.testeiglu.ingredient.domain.Ingredient;
import br.com.testeiglu.menu.domain.MenuOption;
import br.com.testeiglu.menu.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    @Transactional
    void shouldCheckOptionPrices() throws Exception {
        List<MenuOption> options = this.menuService.getAllOptions();

        assertThat(options)
            .isNotEmpty()
            .allMatch(opt ->
                opt.getFullPrice().equals(opt.getIngredients().stream().map(Ingredient::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)));
    }
}
