package io.github.vinicusgaspari.trackerapi.controller.entrypoint.role;

import java.util.UUID;

public record RoleResponse(
        UUID id,
        String nome
) {
}
