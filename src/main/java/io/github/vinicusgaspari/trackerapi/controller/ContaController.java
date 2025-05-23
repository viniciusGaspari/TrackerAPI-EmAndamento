package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta.ContaRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ContaMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta.ContaResponse;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
@Tag(name = "Conta", description = "Gerenciamento de Contas")
public class ContaController implements GenericController {

    private final ContaService service;
    private final ContaMapper mapper;

    @Operation(summary = "Cadastrar nova Conta", description = "Cria uma nova Conta na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Username já cadastrado."),
            @ApiResponse(responseCode = "400", description = "A conta do usuário não tem permissão para o cadastro.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<ContaResponse> salvar(@RequestBody @Valid ContaRequest request) {
        Conta conta = service.salvar(mapper.toEntity(request));
        return ResponseEntity.created(generatorHeaderLocation(conta.getId())).body(mapper.toDTO(conta));
    }

    @Operation(summary = "Buscar Conta por ID", description = "Retorna uma Conta pelo seu identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conta encontrada."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> buscarPorId(@Parameter(description = "UUID da Conta", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(service.buscarPorId(id)));
    }

    @Operation(summary = "Excluir Conta", description = "Remove uma Conta do sistema pelo identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Conta excluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@Parameter(description = "UUID da Conta", required = true) @PathVariable UUID id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar Conta", description = "Atualiza uma Conta existente na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conta atualizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Já existe outra Conta com o username informado."),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> atualizar(@Parameter(description = "UUID da Conta", required = true) @PathVariable UUID id,
                                                   @RequestBody @Valid ContaRequest request) {
        return ResponseEntity.ok(mapper.toDTO(service.atualizar(id, mapper.toEntity(request))));
    }

    @Operation(summary = "Buscar Contas por filtros", description = "Retorna contas com base em critérios opcionais.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Lista de contas encontrada.") })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping
    public ResponseEntity<Page<ContaResponse>> buscarPorFiltro(
            @Parameter(description = "UUID da Conta", example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestParam(value = "id", required = false) UUID id,

            @Parameter(description = "Nome de usuário", example = "joao.silva")
            @RequestParam(value = "username", required = false) String username,

            @Parameter(description = "ID do Role", example = "123e4567-e89b-12d3-a456-426614174000")
            @RequestParam(value = "idRole", required = false) UUID idRole,

            @Parameter(description = "Número da página", example = "0")
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,

            @Parameter(description = "Tamanho da página", example = "5")
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.buscarPorFiltro(id, username, idRole, pagina, tamanhoPagina).map(mapper::toDTO));
    }
}
