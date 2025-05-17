package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.mapper.ContaMapper;
import io.github.vinicusgaspari.trackerapi.controller.response.ContaResponse;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import io.github.vinicusgaspari.trackerapi.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController implements GenericController {

    private final ContaService service;
    private final ContaMapper mapper;

    @PostMapping
    public ResponseEntity<ContaResponse> salvar(@RequestBody @Valid ContaResponse response){
        Conta conta = service.salvar(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(conta.getId())).body(mapper.toDTO(conta));
    }

}
