package io.github.vinicusgaspari.trackerapi.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ClientResponse(

        @Schema(description = "Identificador único do client OAuth. Geralmente fornecido no momento do registro da aplicação.", example = "123456789")
        String clientId,

        @Schema(description = "Segredo do client usado para autenticação segura. Nunca deve ser exposto publicamente.", example = "abcd1234xyz!@#")
        String clientSecret,

        @Schema(description = "URI para onde os usuários são redirecionados após autenticação. Deve ser previamente registrado.", example = "https://example.com/callback")
        String redirectUri,
        
        @Schema(description = "Escopos de acesso permitidos para o client, determinando permissões na API.", example = "admin")
        String scope

) {
}
