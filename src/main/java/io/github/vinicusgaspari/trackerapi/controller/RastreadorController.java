package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.mapper.RastreadorMapper;
import io.github.vinicusgaspari.trackerapi.controller.response.RastreadorResponse;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.service.RastreadorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rastreador")
@Slf4j
public class RastreadorController implements GenericController {

    private final RastreadorService service;
    private final RastreadorMapper mapper;

    @PostMapping
    public ResponseEntity<RastreadorResponse> salvar(@RequestBody RastreadorResponse dto) {
        Rastreador rastreador = service.salvar(mapper.toEntity(dto));
        return ResponseEntity.created(generatorHeaderLocation(rastreador.getId())).body(mapper.toDTO(rastreador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RastreadorResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(service.buscarPorId(id)));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<RastreadorResponse>> buscarPorIdUsuario(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toListDTO(service.buscarPorList(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable UUID id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RastreadorResponse> atualizarPorId(@PathVariable(required = true) UUID id, @RequestBody(required = true) RastreadorResponse response) {
        return ResponseEntity.ok(mapper.toDTO(service.atualizarPorId(id, mapper.toEntity(response))));
    }

    @GetMapping
    public ResponseEntity<Page<RastreadorResponse>> buscarPorFiltro(
            @RequestParam(value = "id", required = false) UUID id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "idUsuario", required = false) UUID idUsuario,
            @RequestParam(value = "idOperadora", required = false) UUID idOperadora,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.buscarPorFiltro(id, nome, idUsuario, idOperadora, pagina, tamanhoPagina).map(mapper::toDTO));
    }

}
