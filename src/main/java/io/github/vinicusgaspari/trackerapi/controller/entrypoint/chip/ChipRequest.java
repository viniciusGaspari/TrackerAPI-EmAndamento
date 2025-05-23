package io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChipRequest(

        @NotBlank
        String nome,

        @NotBlank
        String operadora,

        @NotNull
        Integer linha

) {
}
