package br.com.testeiglu.menu.repository;

import br.com.testeiglu.ingredient.domain.Ingredient;
import br.com.testeiglu.menu.domain.MenuOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<MenuOption> findAll();

    MenuOption findById(@Param("optionId") Long optionId);

}
