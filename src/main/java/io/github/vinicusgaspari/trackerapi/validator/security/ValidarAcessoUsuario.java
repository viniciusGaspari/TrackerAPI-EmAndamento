package io.github.vinicusgaspari.trackerapi.validator.security;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.validator.operadora.ChipValidator;
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
    private final ChipValidator chipValidator;

    public Usuario isAcessoValidoUsuario(UUID idUsuario, String usernameUsuarioLogado) {
        Usuario usuario = usuarioValidator.validarUsuarioPorId(idUsuario);
        if (!usuario.getConta().getUsername().equals(usernameUsuarioLogado)) {
            throw new AcessoNegadoException("USUARIO");
        }
        return usuario;
    }

    public Rastreador isAcessoValidoRasteador(UUID idRastreador, String usernameUsuarioLogado) {
        Rastreador rastreador = rastreadorValidator.buscarRastreadorPorId(idRastreador);
        if (!rastreador.getUsuario().getConta().getUsername().equals(usernameUsuarioLogado)) {
            throw new AcessoNegadoException("RASTREADOR");
        }
        return rastreador;
    }

    public Chip isAcessoValidoChip(UUID idChip, String usernameUsuarioLogado) {
        Chip chip = chipValidator.obterChipPorId(idChip);
        if (!chip.getConta().getUsername().equals(usernameUsuarioLogado)) {
            throw new AcessoNegadoException("CHIP");
        }
        return chip;
    }
}
