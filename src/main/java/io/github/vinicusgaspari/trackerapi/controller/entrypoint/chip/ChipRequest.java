package io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChipRequest(

        @NotBlank
        @Schema(description = "Nome do chip", example = "Chip X")
        String nome,

        @NotBlank
        @Schema(description = "Operadora do chip", example = "Vivo")
        String operadora,

        @NotNull
        @Schema(description = "Número da linha", example = "11987654321")
        Integer linha

) {
}
