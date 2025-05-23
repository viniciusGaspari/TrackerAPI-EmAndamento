package io.github.vinicusgaspari.trackerapi.controller.entrypoint.client;

import java.util.UUID;

public record ClientResponse(
        UUID id,

        String clientId,

        String clientSecret,

        String redirectUri,

        String scope

) {
}
