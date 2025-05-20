package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.controller.response.RastreadorResponse;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RastreadorRepository extends JpaRepository<Rastreador, UUID>, JpaSpecificationExecutor<Rastreador> {
    Integer countByUsuario(Usuario usuario);
    List<Rastreador> findByUsuario(Usuario usuario);
    Optional<Rastreador> findByNome(String nome);
    boolean existsByNome(String nome);
}
