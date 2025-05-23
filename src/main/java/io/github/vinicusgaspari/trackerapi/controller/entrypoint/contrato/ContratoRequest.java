package io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato;

import java.math.BigDecimal;

public record ContratoRequest(
        String nome,
        String quantidade,
        BigDecimal preco
) {
}
