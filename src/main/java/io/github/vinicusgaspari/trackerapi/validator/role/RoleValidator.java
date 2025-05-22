package io.github.vinicusgaspari.trackerapi.validator.role;

import io.github.vinicusgaspari.trackerapi.model.Role;
import io.github.vinicusgaspari.trackerapi.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator {

    private final RoleRepository roleRepository;

    public Role obterRolePorNome(String nome){
        return roleRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("NOME DO ROLE"));
    }

}
