package io.github.vinicusgaspari.trackerapi.controller.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ContratoResponse(
        UUID id,
        String nome,
        String quantidade,
        BigDecimal preco
) {
}
