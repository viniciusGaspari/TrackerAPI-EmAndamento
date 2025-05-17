package io.github.vinicusgaspari.trackerapi.controller.response;

import java.util.List;

public record ErroResposta(
        int status,
        String mensagem,
        List<String> campos
) {
}
