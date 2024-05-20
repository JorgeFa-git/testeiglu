package br.com.testeiglu.menu.domain;

import br.com.testeiglu.core.model.DatabaseObject;
import br.com.testeiglu.ingredient.domain.Ingredient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MenuOption extends DatabaseObject implements Serializable {

    private String name;
    private String imgSrc;
    private List<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getFullPrice() {
        return ingredients.stream().map(Ingredient::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getIngredientsText() {
        return ingredients.stream().map(Ingredient::getName).collect(Collectors.joining(", "));
    }
}
