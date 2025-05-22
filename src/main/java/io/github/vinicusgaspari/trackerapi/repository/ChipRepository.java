package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.model.Chip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChipRepository extends JpaRepository<Chip, UUID> {
    Optional<Chip> findByNome(String nome);

    boolean existsByNome(String nome);
}
