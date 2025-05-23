package io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipResponse;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RastreadorResponse(

        UUID id,

        String nome,

        UsuarioResponse usuario,

        ChipResponse chip

) {
}
