package io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato;

import java.util.UUID;

public record LimiteContratoResponse(
        UUID id,
        String nome
) {
}
