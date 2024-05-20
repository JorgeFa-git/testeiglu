package br.com.testeiglu.menu.service.dto;

import java.util.List;

public class BurgerEdition {
    private List<Long> ingredientIds;

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    @Override
    public String toString() {
        return "BurgerEdition{" +
            "ingredientIds=" + ingredientIds +
            '}';
    }
}
