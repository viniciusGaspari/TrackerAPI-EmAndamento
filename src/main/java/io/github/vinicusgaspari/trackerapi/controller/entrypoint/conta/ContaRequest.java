package io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.role.RoleResponse;

public record ContaRequest(

        String username,

        String password,

        RoleResponse role

) {
}
