package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador.RastreadorRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.RastreadorMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador.RastreadorResponse;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.service.RastreadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rastreador")
@Slf4j
@Tag(name = "Rastreador", description = "Gerenciamento de Rastreadores")
public class RastreadorController implements GenericController{

    private final RastreadorService service;
    private final RastreadorMapper mapper;

    @Operation(summary = "Cadastrar novo Rastreador", description = "Cria um novo Rastreador na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Rastreador já cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<RastreadorResponse> salvar(@RequestBody @Valid RastreadorRequest request) {
        Rastreador rastreador = service.salvar(mapper.toEntity(request));
        return ResponseEntity.created(generatorHeaderLocation(rastreador.getId())).body(mapper.toDTO(rastreador));
    }

    @Operation(summary = "Buscar Rastreador por ID", description = "Retorna um Rastreador pelo seu identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rastreador encontrado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Rastreador não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<RastreadorResponse> buscarPorId(@Parameter(description = "UUID do Rastreador", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(service.buscarPorId(id)));
    }

    @Operation(summary = "Buscar Rastreadores por ID de Usuário", description = "Retorna uma lista de Rastreadores vinculados a um usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de Rastreadores encontrada."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Nenhum Rastreador encontrado para este usuário.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<RastreadorResponse>> buscarPorIdUsuario(@Parameter(description = "UUID do Usuário", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toListDTO(service.buscarPorList(id)));
    }

    @Operation(summary = "Excluir Rastreador", description = "Remove um Rastreador do sistema pelo identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rastreador excluído com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Rastreador não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@Parameter(description = "UUID do Rastreador", required = true) @PathVariable UUID id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar Rastreador", description = "Atualiza um Rastreador existente na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rastreador atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados."),
            @ApiResponse(responseCode = "404", description = "Rastreador não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<RastreadorResponse> atualizarPorId(@Parameter(description = "UUID do Rastreador", required = true) @PathVariable UUID id,
                                                             @RequestBody @Valid RastreadorRequest request) {
        return ResponseEntity.ok(mapper.toDTO(service.atualizarPorId(id, mapper.toEntity(request))));
    }

    @Operation(summary = "Buscar Rastreadores por filtros", description = "Retorna rastreadores com base em critérios opcionais.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Lista de rastreadores encontrada.") })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping
    public ResponseEntity<Page<RastreadorResponse>> buscarPorFiltro(
            @Parameter(description = "UUID do Rastreador", example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestParam(value = "id", required = false) UUID id,

            @Parameter(description = "Nome do Rastreador", example = "Tracker X")
            @RequestParam(value = "nome", required = false) String nome,

            @Parameter(description = "UUID do Usuário", example = "123e4567-e89b-12d3-a456-426614174000")
            @RequestParam(value = "idUsuario", required = false) UUID idUsuario,

            @Parameter(description = "UUID da Operadora", example = "789e1234-e56b-78d9-c012-345678901234")
            @RequestParam(value = "idOperadora", required = false) UUID idOperadora,

            @Parameter(description = "Número da página", example = "0")
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,

            @Parameter(description = "Tamanho da página", example = "5")
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.buscarPorFiltro(id, nome, idUsuario, idOperadora, pagina, tamanhoPagina).map(mapper::toDTO));
    }
}
