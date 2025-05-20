package io.github.vinicusgaspari.trackerapi.validator.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAutenticado {

    public String obtendoUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getClaim("sub");
        }

        throw new RuntimeException("Tipo do principal: " + authentication.getClass().getName());
    }
}
