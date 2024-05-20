package br.com.testeiglu.cart.controller;

import br.com.testeiglu.cart.domain.Cart;
import br.com.testeiglu.cart.service.CartService;
import br.com.testeiglu.core.utils.SessionUtils;
import br.com.testeiglu.burger.domain.Burger;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public ResponseEntity<Cart> getCart(HttpSession session) {
        return ResponseEntity.ok(this.cartService.getCartFromSession(session));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Burger>> getCartBurgers(HttpSession session) {
        return ResponseEntity.ok(SessionUtils.getBurgersFromSession(session));
    }
}
