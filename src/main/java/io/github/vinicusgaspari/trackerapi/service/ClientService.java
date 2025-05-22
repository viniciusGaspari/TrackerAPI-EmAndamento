package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Client;
import io.github.vinicusgaspari.trackerapi.repository.ClientRepository;
import io.github.vinicusgaspari.trackerapi.validator.client.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.vinicusgaspari.trackerapi.validator.client.ClientSpecs.*;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientValidator clientValidator;
    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public Client salvar(Client client) {
        clientValidator.validarContaPorUsernameAoSalvar(client);
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return clientRepository.save(client);
    }

    public Client buscarPorClientId(String clientId) {
        return clientValidator.obterClientPorClientId(clientId);
    }

    public Client buscarPorId(UUID id) {
        return clientValidator.obterClientPorId(id);
    }

    public Client atualizar(UUID id, Client client) {
        Client clientEncontrado = clientValidator.obterClientPorId(id);
        clientValidator.validarContaPorUsernameRedirectUriAoAtualizar(id, client);
        clientEncontrado.setClientId(client.getClientId());
        clientEncontrado.setRedirectUri(client.getRedirectUri());
        clientEncontrado.setClientSecret(encoder.encode(client.getClientSecret()));
        clientEncontrado.setScope(client.getScope());
        return clientEncontrado;
    }

    public void deletePorId(UUID id) {
        clientRepository.delete(clientValidator.obterClientPorId(id));
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

        return clientRepository.findAll(specs, PageRequest.of(pagina, tamanhoPagina));

    }

}
