package io.github.vinicusgaspari.trackerapi.validator.security;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.validator.rastreador.RastreadorValidator;
import io.github.vinicusgaspari.trackerapi.validator.usuario.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidarAcessoUsuario {

    private final UsuarioValidator usuarioValidator;
    private final RastreadorValidator rastreadorValidator;

    public Usuario isAcessoValidoUsuario(UUID idUsuario, String usernameUsuarioLogado) {
        Usuario usuario = usuarioValidator.validarUsuarioPorId(idUsuario);
        if (!usuario.getConta().getUsername().equals(usernameUsuarioLogado)) {
            throw new AcessoNegadoException("Conta não tem acesso");
        }
        return usuario;
    }

    public Rastreador isAcessoValidoRasteador(UUID idUsuario, String usernameUsuarioLogado) {
        Rastreador rastreador = rastreadorValidator.buscarRastreadorPorId(idUsuario);
        if (!rastreador.getUsuario().getConta().getUsername().equals(usernameUsuarioLogado)) {
            throw new AcessoNegadoException("Conta não tem acesso");
        }
        return rastreador;
    }
}
