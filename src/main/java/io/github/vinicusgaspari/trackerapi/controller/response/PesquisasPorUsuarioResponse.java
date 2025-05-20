package io.github.vinicusgaspari.trackerapi.controller.response;

public record PesquisasPorUsuarioResponse(
        String nome,
        String cidade,
        ContratoResponse contrato
) {
}
