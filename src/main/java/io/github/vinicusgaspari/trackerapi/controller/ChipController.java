package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.mapper.ChipMapper;
import io.github.vinicusgaspari.trackerapi.controller.response.ChipResponse;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.service.ChipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chip")
@RequiredArgsConstructor
public class ChipController implements GenericController {

    private final ChipService chipService;
    private final ChipMapper mapper;

    @PostMapping
    public ResponseEntity<ChipResponse> salvar(@RequestBody(required = true) @Valid ChipResponse response) {
        Chip chip = chipService.salvar(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(chip.getId())).body(mapper.toDTO(chip));
    }

    @GetMapping("{id}")
    public ResponseEntity<ChipResponse> obterPorId(@PathVariable(required = true) UUID id) {
        return ResponseEntity.ok(mapper.toDTO(chipService.buscarPorId(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ChipResponse> deletarPorId(@PathVariable(required = true) UUID id) {
        chipService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ChipResponse> atualizar(@PathVariable(required = true) UUID id, @RequestBody(required = true) @Valid ChipResponse response) {
        return ResponseEntity.ok(mapper.toDTO(chipService.atualizar(id, mapper.toEntity(response))));
    }

}
