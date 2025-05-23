package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.ChipMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipResponse;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import io.github.vinicusgaspari.trackerapi.service.ChipService;
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
public class ChipController implements GenericController {

    private final ChipService chipService;
    private final ChipMapper mapper;

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    public ResponseEntity<ChipResponse> salvar(@RequestBody(required = true) @Valid ChipRequest response) {
        Chip chip = chipService.salvar(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(chip.getId())).body(mapper.toDTO(chip));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("{id}")
    public ResponseEntity<ChipResponse> obterPorId(@PathVariable(required = true) UUID id) {
        return ResponseEntity.ok(mapper.toDTO(chipService.buscarPorId(id)));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("{id}")
    public ResponseEntity<ChipResponse> deletarPorId(@PathVariable(required = true) UUID id) {
        chipService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("{id}")
    public ResponseEntity<ChipResponse> atualizar(@PathVariable(required = true) UUID id, @RequestBody(required = true) @Valid ChipRequest response) {
        return ResponseEntity.ok(mapper.toDTO(chipService.atualizar(id, mapper.toEntity(response))));
    }

}
