package br.com.testeiglu.burger.controller;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.burger.service.BurgerService;
import br.com.testeiglu.core.utils.SessionUtils;
import br.com.testeiglu.menu.service.dto.BurgerEdition;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("burger")
public class BurgerResource {

    private final BurgerService burgerService;

    public BurgerResource(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @PostMapping("/personalized")
    public ResponseEntity<Burger> addBurger(@RequestBody BurgerEdition burgerEdition, HttpSession httpSession) {
        var burger = this.burgerService.createCustomBurger(burgerEdition);
        SessionUtils.addBurgerToSession(burger, httpSession);
        return ResponseEntity.status(201).body(burger);
    }

    @PostMapping("/from-menu-option/{optionId}")
    public ResponseEntity<Burger> addBurgerFromMenuOption(@PathVariable Long optionId, HttpSession httpSession) {
        var burger = this.burgerService.createBurgerFromMenuOption(optionId);
        SessionUtils.addBurgerToSession(burger, httpSession);
        return ResponseEntity.status(201).body(burger);
    }

}
