package io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip;

import java.util.UUID;

public record ChipResponse(

        UUID id,

        String nome,

        String operadora,

        Integer linha

) {
}
