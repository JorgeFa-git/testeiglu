package br.com.testeiglu.menu.controller;

import br.com.testeiglu.ingredient.service.IngredientService;
import br.com.testeiglu.menu.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    private final MenuService menuService;
    private final IngredientService ingredientService;

    public MenuController(MenuService menuService, IngredientService ingredientService) {
        this.menuService = menuService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        var modelAndView = new ModelAndView("index");
        var ingredientList = this.ingredientService.getAllIngredients();
        var menuOptions = this.menuService.getAllOptions();

        modelAndView.addObject("options", menuOptions);
        modelAndView.addObject("ingredients", ingredientList);
        return modelAndView;
    }



}
