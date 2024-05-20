package br.com.testeiglu.cart.service;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.cart.domain.Cart;
import br.com.testeiglu.core.utils.SessionUtils;
import br.com.testeiglu.discount.domain.DiscountEnum;
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
        var totalDiscount = this.calculateTotalDiscount(burgersFromSession);
        return new Cart(burgersFromSession, totalDiscount, total.subtract(totalDiscount));
    }

    public List<Ingredient> getAllIngredientsFromBurgerList(List<Burger> burgers) {
        List<Ingredient> list = new ArrayList<>();
        for (Burger burger : burgers) {
            list.addAll(burger.getIngredients());
        }
        return list;
    }

    private BigDecimal calculateTotalDiscount(List<Burger> burgersFromSession) {
        var totalDiscount = BigDecimal.ZERO;

        if (burgersFromSession.stream().anyMatch(burger -> burger.getDiscounts().contains(DiscountEnum.LIGHT))) {
            var discountBurgers = burgersFromSession.stream().filter(burger -> burger.getDiscounts().contains(DiscountEnum.LIGHT)).toList();
            for (Burger burger : discountBurgers) {
                var burgerValue = this.calculateTotal(burger.getIngredients());
                totalDiscount = totalDiscount.add(burgerValue.multiply(BigDecimal.valueOf(10 / 100f)));
            }
        }

        if (burgersFromSession.stream().anyMatch(burger -> burger.getDiscounts().contains(DiscountEnum.MUITA_CARNE))) {
            var discountBurgers = burgersFromSession.stream().filter(burger -> burger.getDiscounts().contains(DiscountEnum.MUITA_CARNE)).toList();
            for (Burger burger : discountBurgers) {
                var meatIngredients = burger.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(100003L)).toList();
                var count = meatIngredients.size();
                var totalToDiscount = count / 3;
                totalDiscount = totalDiscount.add(meatIngredients.getFirst().getPrice().multiply(BigDecimal.valueOf(totalToDiscount)));
            }
        }

        if (burgersFromSession.stream().anyMatch(burger -> burger.getDiscounts().contains(DiscountEnum.MUITO_QUEIJO))) {
            var discountBurgers = burgersFromSession.stream().filter(burger -> burger.getDiscounts().contains(DiscountEnum.MUITO_QUEIJO)).toList();
            for (Burger burger : discountBurgers) {
                var cheeseIngredients = burger.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(100005L)).toList();
                var count = cheeseIngredients.size();
                var totalToDiscount = count / 3;
                totalDiscount = totalDiscount.add(cheeseIngredients.getFirst().getPrice().multiply(BigDecimal.valueOf(totalToDiscount)));
            }
        }

        return totalDiscount;
    }
}
