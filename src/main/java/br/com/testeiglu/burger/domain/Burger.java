package br.com.testeiglu.burger.domain;

import br.com.testeiglu.core.model.DatabaseObject;
import br.com.testeiglu.discount.domain.DiscountEnum;
import br.com.testeiglu.ingredient.domain.Ingredient;

import java.io.Serializable;
import java.util.List;

public class Burger extends DatabaseObject implements Serializable {

    private String name;
    private List<Ingredient> ingredients;
    private List<DiscountEnum> discounts;

    public Burger() {
    }

    public Burger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<DiscountEnum> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountEnum> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "Burger{" +
            "name='" + name + '\'' +
            ", ingredients=" + ingredients +
            ", discounts=" + discounts +
            '}';
    }
}
