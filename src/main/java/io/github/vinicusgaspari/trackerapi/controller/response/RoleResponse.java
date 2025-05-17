package io.github.vinicusgaspari.trackerapi.controller.response;

import java.util.UUID;

public record RoleResponse(
        UUID id,
        String nome
) {
}
