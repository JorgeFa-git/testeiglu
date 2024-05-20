package br.com.testeiglu.ingredient.domain;

import br.com.testeiglu.core.model.DatabaseObject;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ingredient extends DatabaseObject implements Serializable {

    private String name;
    private BigDecimal price;
    private int qty;

    public Ingredient() {
    }

    public Ingredient(Long id) {
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
            "price=" + price +
            ", name='" + name + '\'' +
            '}';
    }
}
