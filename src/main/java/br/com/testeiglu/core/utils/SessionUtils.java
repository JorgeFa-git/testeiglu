package br.com.testeiglu.core.utils;

import br.com.testeiglu.burger.domain.Burger;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionUtils {

    private static final String BURGERS_SESSION_KEY = "burgers";

    private SessionUtils() {}

    public static List<Burger> getBurgersFromSession(HttpSession session) {
        return Optional.ofNullable((List<Burger>)session.getAttribute(BURGERS_SESSION_KEY)).orElse(new ArrayList<>());
    }

    public static void addBurgerToSession(Burger burger, HttpSession httpSession) {
        var sessionBurgers = SessionUtils.getBurgersFromSession(httpSession);
        sessionBurgers.add(burger);
        httpSession.setAttribute(BURGERS_SESSION_KEY, sessionBurgers);
    }
}
