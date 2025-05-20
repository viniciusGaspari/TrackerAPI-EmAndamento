package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {

    Optional<Contrato> findByNome(String nome);

}
