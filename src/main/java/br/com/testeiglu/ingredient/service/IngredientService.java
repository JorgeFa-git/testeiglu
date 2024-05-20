package br.com.testeiglu.ingredient.service;

import br.com.testeiglu.ingredient.repository.IngredientMapper;
import br.com.testeiglu.ingredient.domain.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IngredientService {

    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public List<Ingredient> getAllIngredients() {
        return this.ingredientMapper.findAll();
    }
}
