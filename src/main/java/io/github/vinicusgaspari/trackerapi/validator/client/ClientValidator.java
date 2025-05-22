package io.github.vinicusgaspari.trackerapi.validator.client;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.model.Client;
import io.github.vinicusgaspari.trackerapi.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository clientRepository;

    public Client obterClientPorClientId(String clientId){
        return clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException("CLIENTID DO CLIENT"));
    }

    public Client obterClientPorId(UUID id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID DO CLIENT"));
    }

    public void validarContaPorUsernameAoSalvar(Client client) {
        List<String> duplicatedField = new ArrayList<>();

        if (clientRepository.existsByClientId(client.getClientId())) {
            duplicatedField.add("CLIENTID DO CLIENT");
        }

        if (clientRepository.existsByRedirectUri(client.getRedirectUri())) {
            duplicatedField.add("REDIRECTURI DO CLIENT");
        }

        if (!duplicatedField.isEmpty()) {
            throw new DadoDuplicadoException(duplicatedField);
        }

    }

    public void validarContaPorUsernameRedirectUriAoAtualizar(UUID id, Client client) {
        List<String> duplicatedField = new ArrayList<>();

        Optional<Client> clientIdEncontrado = clientRepository.findByClientId(client.getClientId());

        if(clientIdEncontrado.isPresent()){
            if(!clientIdEncontrado.get().getId().equals(id)){
                duplicatedField.add("CLIENTID DO CLIENT");
            }
        }

        Optional<Client> clientRedirectUriEncontrado = clientRepository.findByRedirectUri(client.getRedirectUri());

        if(clientRedirectUriEncontrado.isPresent()){
            if(!clientRedirectUriEncontrado.get().getId().equals(id)){
                duplicatedField.add("REDIRECTURI DO CLIENT");
            }
        }

        if (!duplicatedField.isEmpty()) {
            throw new DadoDuplicadoException(duplicatedField);
        }
    }


}
