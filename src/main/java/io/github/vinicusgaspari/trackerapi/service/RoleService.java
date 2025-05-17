package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Role;
import io.github.vinicusgaspari.trackerapi.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Entidade role não encontrada"));
    }

}
