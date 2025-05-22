package io.github.vinicusgaspari.trackerapi.validator.security;

import io.github.vinicusgaspari.trackerapi.security.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAutenticado {

    public String obterUsernameUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication auth) {
            return auth.getName();
        }

        throw new RuntimeException("Tipo do principal: " + authentication.getClass().getName());
    }
}
