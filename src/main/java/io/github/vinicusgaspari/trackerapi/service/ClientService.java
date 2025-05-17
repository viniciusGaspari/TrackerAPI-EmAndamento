package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Client;
import io.github.vinicusgaspari.trackerapi.repository.ClientRepository;
import io.github.vinicusgaspari.trackerapi.validator.client.ValidarDadosDuplicadosClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.github.vinicusgaspari.trackerapi.validator.client.ClientSpecs.*;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ValidarDadosDuplicadosClient validator;
    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client) {
        validator.checkDuplicatedDataToSave(client);
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return repository.save(client);
    }

    public Client findByClientId(String clientId) {
        return repository.findByClientId(clientId).get();
    }

    public Client findById(UUID id) {
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return client.get();
    }

    public Client update(UUID id, Client client) {
        Client clientFound = findById(id);
        clientFound.setClientId(client.getClientId());
        clientFound.setRedirectUri(client.getRedirectUri());
        validator.checkDuplicatedDataToUpdate(id, clientFound);
        clientFound.setClientSecret(encoder.encode(client.getClientSecret()));
        clientFound.setScope(client.getScope());
        return repository.save(clientFound);
    }

    public void delete(UUID id) {
        if (repository.existsById(id)) {
            repository.delete(repository.findById(id).get());
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Page<Client> buscarPorFiltro(UUID id, String clientId, String redirectUri, String scope, Integer pagina, Integer tamanhoPagina) {

        Specification<Client> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (id != null) {
            specs = specs.and(isIdEqual(id));
        }
        if (clientId != null) {
            specs = specs.and(isClientIdLike(clientId));
        }
        if (redirectUri != null) {
            specs = specs.and(isRedirectUriLike(redirectUri));
        }
        if (scope != null) {
            specs = specs.and(isScopeLike(scope));
        }

        return repository.findAll(specs, PageRequest.of(pagina, tamanhoPagina));

    }

}
