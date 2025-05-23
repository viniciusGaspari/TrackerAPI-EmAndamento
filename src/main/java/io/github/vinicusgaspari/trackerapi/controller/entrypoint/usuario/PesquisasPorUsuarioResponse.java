package io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoResponse;

public record PesquisasPorUsuarioResponse(
        String nome,
        String cidade,
        ContratoResponse contrato
) {
}
