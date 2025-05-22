package io.github.vinicusgaspari.trackerapi.security;

import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.validator.conta.ContaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ContaValidator contaValidator;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Conta conta = contaValidator.obterContaPorUsername(authentication.getName());

        if (conta == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        if (!encoder.matches(authentication.getCredentials().toString(), conta.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        Authentication auth = new CustomAuthentication(conta);
        SecurityContextHolder.getContext().setAuthentication(auth); // 🔥 Armazena no contexto de segurança
        return auth;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
