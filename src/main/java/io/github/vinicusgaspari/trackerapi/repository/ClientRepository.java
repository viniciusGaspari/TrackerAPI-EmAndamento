package io.github.vinicusgaspari.trackerapi.repository;

import io.github.vinicusgaspari.trackerapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID>, JpaSpecificationExecutor<Client> {

    boolean existsByClientId(String clientId);

    boolean existsByRedirectUri(String redirectUri);

    Optional<Client> findByClientId(String clientId);

    Optional<Client> findByRedirectUri(String redirectUri);
}
