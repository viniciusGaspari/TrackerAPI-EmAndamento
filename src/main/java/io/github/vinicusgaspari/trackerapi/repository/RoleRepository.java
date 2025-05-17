package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByNome(String nome);

}
