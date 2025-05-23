package io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.role.RoleResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContaResponse(

        UUID id,

        String username,

        String password,

        RoleResponse role
) {
}
