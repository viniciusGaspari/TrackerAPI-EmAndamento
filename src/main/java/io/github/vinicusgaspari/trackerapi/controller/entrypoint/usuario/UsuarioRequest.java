package io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta.ContaResponse;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
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
