package br.com.testeiglu.burger.service;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.discount.domain.DiscountEnum;
import br.com.testeiglu.ingredient.domain.Ingredient;
import br.com.testeiglu.menu.domain.MenuOption;
import br.com.testeiglu.burger.repository.BurgerMapper;
import br.com.testeiglu.menu.repository.MenuMapper;
import br.com.testeiglu.menu.service.dto.BurgerEdition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BurgerService {

    private static final String CUSTOM_BURGER_NAME = "Custom";

    private final BurgerMapper burgerMapper;

    private final MenuMapper menuMapper;

    public BurgerService(BurgerMapper burgerMapper, MenuMapper menuRepository) {
        this.burgerMapper = burgerMapper;
        this.menuMapper = menuRepository;
    }

    public Burger createCustomBurger(BurgerEdition burgerEdition) {
        return createBurger(CUSTOM_BURGER_NAME, burgerEdition.getIngredientIds());
    }

    public Burger createBurgerFromMenuOption(Long optionId) {
        MenuOption menuOption = this.menuMapper.findById(optionId);
        return createBurger(menuOption.getName(), menuOption.getIngredients().stream().map(Ingredient::getId).toList());
    }

    private Burger createBurger(String name, List<Long> ingredientIds) {
        Burger burger = new Burger(name);
        this.burgerMapper.saveBurger(burger);
        this.burgerMapper.relateIngredientsToBurger(burger.getId(), ingredientIds);
        if (!getDiscounts(ingredientIds).isEmpty()) {
            this.burgerMapper.addDiscountsToBurger(burger.getId(), getDiscounts(ingredientIds));
        }

        return this.burgerMapper.findById(burger.getId());
    }

    private List<DiscountEnum> getDiscounts(List<Long> ingredientIds) {
        var discounts = new ArrayList<DiscountEnum>();

        if (ingredientIds.stream().anyMatch(ing -> ing.equals(100001L)) && ingredientIds.stream().noneMatch(ing -> ing.equals(100002L))) {
            discounts.add(DiscountEnum.LIGHT);
        }

        var meatCount = ingredientIds.stream().filter(ing -> ing.equals(100003L)).toList().size();
        if (meatCount > 0 && meatCount % 3 == 0) {
            discounts.add(DiscountEnum.MUITA_CARNE);
        }

        var cheeseCount = ingredientIds.stream().filter(ing -> ing.equals(100005L)).toList().size();
        if (cheeseCount > 0 && cheeseCount % 3 == 0) {
            discounts.add(DiscountEnum.MUITO_QUEIJO);
        }

        return discounts;
    }
}
