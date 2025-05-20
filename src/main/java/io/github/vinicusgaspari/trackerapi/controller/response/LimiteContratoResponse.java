package io.github.vinicusgaspari.trackerapi.controller.response;

import java.util.UUID;

public record LimiteContratoResponse(
        UUID id,
        String nome
) {
}
