package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.response.ClientResponse;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ClientMapper;
import io.github.vinicusgaspari.trackerapi.model.Client;
import io.github.vinicusgaspari.trackerapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@Slf4j
@Tag(name = "Client")
@RequiredArgsConstructor
public class ClientController implements GenericController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @Operation(
            summary = "Cadastrar novo client",
            description = "Registra um novo client na base de dados. O campo `clientId` e `clientSecret` precisam ser exclusivos. "
    )
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Client já cadastrado"),
            @ApiResponse(responseCode = "422", description = "Erro de validação nos dados")
    })
    public ResponseEntity<ClientResponse> save(@RequestBody(required = true) ClientResponse response) {
        Client client = service.save(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(client.getId())).body(mapper.toDTO(client));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Client OAuth2 por ID",
            description = "Este endpoint permite buscar um Client registrado no fluxo OAuth2 Authorization Code pelo seu identificador único (`ID`). ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client OAuth2 encontrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID. O valor deve ser um UUID válido."),
            @ApiResponse(responseCode = "404", description = "Nenhum Client OAuth2 encontrado para o ID informado.")})
    public ResponseEntity<ClientResponse> findByIdClient(
            @Parameter(description = "UUID do Client OAuth2", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(service.findById(id)));
    }

    @GetMapping
    @Operation(
            summary = "Buscar Client por filtros",
            description = "Permite buscar um Client na base de dados utilizando diferentes filtros opcionais. "
                    + "Os parâmetros podem ser combinados para refinar a busca."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client encontrado com sucesso.")
    })
    public ResponseEntity<Page<ClientResponse>> findByFilters(
            @Parameter(description = "UUID do Client", example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestParam(value = "id", required = false) UUID id,

            @Parameter(description = "Identificador único do Client", example = "123456")
            @RequestParam(value = "clientId", required = false) String clientId,

            @Parameter(description = "URI de redirecionamento do Client", example = "https://example.com/callback")
            @RequestParam(value = "redirectUri", required = false) String redirectUri,

            @Parameter(description = "Escopos de acesso permitidos para o Client", example = "admin")
            @RequestParam(value = "scope", required = false) String scope,

            @Parameter(description = "Número da página a ser retornada (paginação começa em 0)", example = "0")
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,

            @Parameter(description = "Quantidade de registros por página", example = "10")
            @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        Page<Client> pageResult = service.buscarPorFiltro(id, clientId, redirectUri, scope, pagina, tamanhoPagina);
        return ResponseEntity.ok(pageResult.map(mapper::toDTO));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Client",
            description = "Atualiza os dados de um Client existente na base de dados pelo seu identificador único (`ID`). "
                    + "O corpo da requisição deve conter as novas informações do Client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID ou dados incorretos."),
            @ApiResponse(responseCode = "404", description = "Nenhum Client encontrado para o ID informado.")})
    public ResponseEntity<ClientResponse> atualizar(
            @Parameter(description = "UUID do Client", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable(required = true) UUID id,

            @RequestBody(required = true)
            ClientResponse response
    ) {
        return ResponseEntity.ok(mapper.toDTO(service.update(id, mapper.toEntity(response))));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar Client",
            description = "Remove um Client da base de dados pelo seu identificador único (`ID`). ")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Nenhum Client encontrado para o ID informado.")})
    public ResponseEntity<Void> delete(
            @Parameter(description = "UUID do Client", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable(required = true) UUID id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
