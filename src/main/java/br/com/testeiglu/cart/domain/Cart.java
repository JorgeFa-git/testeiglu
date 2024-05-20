package br.com.testeiglu.cart.domain;

import br.com.testeiglu.burger.domain.Burger;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
    private List<Burger> burgers;
    private BigDecimal discount;
    private BigDecimal total;

    public Cart(List<Burger> burgersFromSession, BigDecimal discount, BigDecimal bigDecimal) {
        this.burgers = burgersFromSession;
        this.discount = discount;
        this.total = bigDecimal;
    }

    public List<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(List<Burger> burgers) {
        this.burgers = burgers;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
