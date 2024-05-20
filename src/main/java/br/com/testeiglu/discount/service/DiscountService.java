package br.com.testeiglu.discount.service;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.discount.domain.DiscountEnum;
import br.com.testeiglu.ingredient.domain.Ingredient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountService {

    public BigDecimal calculateTotalDiscount(List<Burger> burgersFromSession) {
        var totalDiscount = BigDecimal.ZERO;

        if (burgersFromSession.stream().anyMatch(burger -> burger.getDiscounts().contains(DiscountEnum.LIGHT))) {
            var discountBurgers = burgersFromSession.stream().filter(burger -> burger.getDiscounts().contains(DiscountEnum.LIGHT)).toList();
            for (Burger burger : discountBurgers) {
                var burgerValue = burger.getIngredients().stream().map(Ingredient::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
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
