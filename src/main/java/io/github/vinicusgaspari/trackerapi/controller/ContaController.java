package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.mapper.ContaMapper;
import io.github.vinicusgaspari.trackerapi.controller.response.ContaResponse;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController implements GenericController {

    private final ContaService service;
    private final ContaMapper mapper;

    @PostMapping
    public ResponseEntity<ContaResponse> salvar(@RequestBody @Valid ContaResponse response) {
        Conta conta = service.salvar(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(conta.getId())).body(mapper.toDTO(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> buscarPorId(@PathVariable(required = true)UUID id){
        return ResponseEntity.ok(mapper.toDTO(service.buscarPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable(required = true) UUID id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> atualizar(@PathVariable(required = true) UUID id, @RequestBody(required = true) ContaResponse response){
        return ResponseEntity.ok(mapper.toDTO(service.atualizar(id, mapper.toEntity(response))));
    }

    @GetMapping
    public ResponseEntity<Page<ContaResponse>> buscarPorFiltro(
            @RequestParam(required = false, value = "id") UUID id,
            @RequestParam(required = false, value = "username") String username,
            @RequestParam(required = false, value = "idRole") UUID idRole,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ){
        return ResponseEntity.ok(service.buscarPorFiltro(id, username, idRole, pagina, tamanhoPagina).map(mapper::toDTO));
    }

}
