package io.github.vinicusgaspari.trackerapi.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UsuarioResponse(

        @Schema(description = "Identificador único do usuário.", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Nome completo do usuário.", example = "João Silva")
        @NotBlank
        String nome,

        @Schema(description = "Cidade de residência do usuário.", example = "São Paulo")
        @NotBlank
        String cidade,

        @NotNull
        ContratoResponse contrato,

        ContaResponse conta

) {
}
