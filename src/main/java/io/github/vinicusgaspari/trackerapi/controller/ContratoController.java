package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ContratoMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoResponse;
import io.github.vinicusgaspari.trackerapi.service.ContratoService;
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

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/contrato")
@RequiredArgsConstructor
@Tag(name = "Contrato", description = "Gerenciamento de Contratos")
public class ContratoController {

    private final ContratoService contratoService;
    private final ContratoMapper mapper;

    @Operation(summary = "Cadastrar novo Contrato", description = "Cria um novo Contrato na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Contrato já cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<ContratoResponse> salvar(@RequestBody @Valid ContratoRequest request) {
        return ResponseEntity.ok(mapper.toDTO(contratoService.salvar(mapper.toEntity(request))));
    }

    @Operation(summary = "Buscar Contrato por ID", description = "Retorna um Contrato pelo seu identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato encontrado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponse> buscarPorId(@Parameter(description = "UUID do Contrato", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(contratoService.buscarPorId(id)));
    }

    @Operation(summary = "Excluir Contrato", description = "Remove um Contrato do sistema pelo identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrato excluído com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@Parameter(description = "UUID do Contrato", required = true) @PathVariable UUID id) {
        contratoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar Contrato", description = "Atualiza um Contrato existente na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrato atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados."),
            @ApiResponse(responseCode = "404", description = "Contrato não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponse> atualizar(@Parameter(description = "UUID do Contrato", required = true) @PathVariable UUID id,
                                                      @RequestBody @Valid ContratoRequest request) {
        return ResponseEntity.ok(mapper.toDTO(contratoService.atualizar(id, mapper.toEntity(request))));
    }

    @Operation(summary = "Buscar Contratos por filtros", description = "Retorna contratos com base em critérios opcionais.")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Lista de contratos encontrada.") })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping
    public ResponseEntity<Page<ContratoResponse>> buscarPorFiltro(
            @Parameter(description = "UUID do Contrato", example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestParam(value = "id", required = false) UUID id,

            @Parameter(description = "Nome do Contrato", example = "Plano Premium")
            @RequestParam(value = "nome", required = false) String nome,

            @Parameter(description = "Preço do Contrato", example = "99.90")
            @RequestParam(value = "preco", required = false) BigDecimal preco,

            @Parameter(description = "Quantidade de contratos ativos", example = "100")
            @RequestParam(value = "quantidade", required = false) Integer quantidade,

            @Parameter(description = "Número da página", example = "0")
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,

            @Parameter(description = "Tamanho da página", example = "5")
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(contratoService.buscarPorFiltro(id, nome, preco, quantidade, pagina, tamanhoPagina).map(mapper::toDTO));
    }
}
