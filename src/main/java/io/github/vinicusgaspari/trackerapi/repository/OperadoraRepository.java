package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.model.Operadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperadoraRepository extends JpaRepository<Operadora, UUID> {
}
