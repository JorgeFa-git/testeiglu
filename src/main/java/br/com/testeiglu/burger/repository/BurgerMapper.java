package br.com.testeiglu.burger.repository;

import br.com.testeiglu.burger.domain.Burger;
import br.com.testeiglu.discount.domain.DiscountEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BurgerMapper {

    void saveBurger(@Param("burger") Burger burger);

    void relateIngredientsToBurger(@Param("burgerId") Long burgerId, @Param("ingredientsId") List<Long> ingredientsId);

    Burger findById(@Param("burgerId") Long burgerId);

    void addDiscountsToBurger(@Param("burgerId") Long id, @Param("discountEnums") List<DiscountEnum> discountEnums);
}
