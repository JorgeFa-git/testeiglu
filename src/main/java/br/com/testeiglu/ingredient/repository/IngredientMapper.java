package br.com.testeiglu.ingredient.repository;

import br.com.testeiglu.ingredient.domain.Ingredient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngredientMapper {

    List<Ingredient> findAll();
}
