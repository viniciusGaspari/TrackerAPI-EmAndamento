package io.github.vinicusgaspari.trackerapi.controller.entrypoint.exceptions;

import java.util.List;

public record ErroRespostaResponse(
        int status,
        String mensagem,
        List<String> campos
) {
}
