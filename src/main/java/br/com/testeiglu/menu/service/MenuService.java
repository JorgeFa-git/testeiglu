package br.com.testeiglu.menu.service;

import br.com.testeiglu.menu.domain.MenuOption;
import br.com.testeiglu.menu.repository.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuOption> getAllOptions() {
        return this.menuMapper.findAll();
    }
}
