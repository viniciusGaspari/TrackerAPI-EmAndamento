package io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipResponse;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioResponse;

import java.util.UUID;

public record RastreadorRequest(
        String nome,

        UsuarioResponse usuario,

        ChipResponse chip
) {
}
