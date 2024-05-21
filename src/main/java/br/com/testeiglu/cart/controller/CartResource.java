package br.com.testeiglu.cart.controller;

import br.com.testeiglu.cart.domain.Cart;
import br.com.testeiglu.cart.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartResource {

    private final CartService cartService;

    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ResponseEntity<Cart> getCart(HttpSession session) {
        return ResponseEntity.ok(this.cartService.getCartFromSession(session));
    }
}
