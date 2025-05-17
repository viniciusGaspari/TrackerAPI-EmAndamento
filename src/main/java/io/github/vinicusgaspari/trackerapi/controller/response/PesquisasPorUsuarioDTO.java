package io.github.vinicusgaspari.trackerapi.controller.response;

public record PesquisasPorUsuarioDTO(
        String nome,
        String cidade,
        ContratoResponse contrato
) {
}
