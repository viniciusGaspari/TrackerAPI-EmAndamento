package io.github.vinicusgaspari.trackerapi.controller.response;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record OperadoraResponse(

        UUID id,

        @NotBlank
        String nome,

        @NotBlank
        BigDecimal linha

) {
}
