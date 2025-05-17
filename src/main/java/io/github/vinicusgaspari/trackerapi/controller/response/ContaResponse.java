package io.github.vinicusgaspari.trackerapi.controller.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContaResponse(

        UUID id,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotNull
        RoleResponse role
) {
}
