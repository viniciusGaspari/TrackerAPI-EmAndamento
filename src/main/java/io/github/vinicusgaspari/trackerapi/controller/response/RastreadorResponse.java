package io.github.vinicusgaspari.trackerapi.controller.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RastreadorResponse(

        UUID id,

        @NotBlank
        String nome,

        UsuarioResponse usuario,

        @NotNull
        ChipResponse chip

) {
}
