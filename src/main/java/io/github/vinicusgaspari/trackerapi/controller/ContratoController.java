package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ContratoMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoResponse;
import io.github.vinicusgaspari.trackerapi.service.ContratoService;
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
public class ContratoController {

    private final ContratoService contratoService;
    private final ContratoMapper mapper;

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<ContratoResponse> salvar(@RequestBody(required = true) @Valid ContratoRequest response){
        return ResponseEntity.ok(mapper.toDTO(contratoService.salvar(mapper.toEntity(response))));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")

    @GetMapping("/{id}")
    public ResponseEntity<ContratoResponse> buscarPorId(@PathVariable(required = true) UUID id){
        return ResponseEntity.ok(mapper.toDTO(contratoService.buscarPorId(id)));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable(required = true) UUID id){
        contratoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponse> atualizar(@PathVariable(required = true) UUID id, @RequestBody(required = true) @Valid ContratoRequest response){
        return ResponseEntity.ok(mapper.toDTO(contratoService.atualizar(id, mapper.toEntity(response))));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping
    public ResponseEntity<Page<ContratoResponse>> buscarPorFiltro(
            @RequestParam(required = false, value = "id") UUID id,
            @RequestParam(required = false, value = "id") String nome,
            @RequestParam(required = false, value = "preco") BigDecimal preco,
            @RequestParam(required = false, value = "quantidade") Integer quantidade,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ){
        return ResponseEntity.ok(contratoService.buscarPorFiltro(id, nome, preco, quantidade, pagina, tamanhoPagina).map(mapper::toDTO));
    }



}


