package io.github.vinicusgaspari.trackerapi.controller.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ChipResponse(

        UUID id,

        @NotBlank
        String nome,

        @NotBlank
        String operadora,

        @NotNull
        Integer linha

) {
}
