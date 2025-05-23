package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ChipMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipResponse;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.service.ChipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RestController
@RequestMapping("/chip")
@RequiredArgsConstructor
@Tag(name = "Chip", description = "Gerenciamento de Chips")
public class ChipController implements GenericController {

    private final ChipService chipService;
    private final ChipMapper mapper;

    @Operation(summary = "Cadastrar novo Chip", description = "Cria um novo Chip na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Nome do Chip já cadastrado."),
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<ChipResponse> salvar(@RequestBody @Valid ChipRequest request) {
        Chip chip = chipService.salvar(mapper.toEntity(request));
        return ResponseEntity.created(generatorHeaderLocation(chip.getId())).body(mapper.toDTO(chip));
    }

    @Operation(summary = "Buscar Chip por ID", description = "Retorna um Chip pelo seu identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chip encontrado."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Chip não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("{id}")
    public ResponseEntity<ChipResponse> obterPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(chipService.buscarPorId(id)));
    }

    @Operation(summary = "Excluir Chip", description = "Remove um Chip do sistema pelo identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Chip excluído com sucesso."),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID."),
            @ApiResponse(responseCode = "404", description = "Chip não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable UUID id) {
        chipService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar Chip", description = "Atualiza um Chip existente na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chip atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Já existe outro Chip com o nome informado."),
            @ApiResponse(responseCode = "404", description = "Chip não encontrado.")
    })
    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("{id}")
    public ResponseEntity<ChipResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ChipRequest request) {
        return ResponseEntity.ok(mapper.toDTO(chipService.atualizar(id, mapper.toEntity(request))));
    }
}
