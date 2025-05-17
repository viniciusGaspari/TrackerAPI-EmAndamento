package io.github.vinicusgaspari.trackerapi.validator.client;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DuplicatedDataException;
import io.github.vinicusgaspari.trackerapi.model.Client;
import io.github.vinicusgaspari.trackerapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidarDadosDuplicadosClient {

    private final ClientRepository repository;

    public void checkDuplicatedDataToSave(Client client) {
        List<String> duplicatedField = new ArrayList<>();

        if (repository.existsByClientId(client.getClientId())) {
            duplicatedField.add("ClientId");
        }

        if (repository.existsByRedirectUri(client.getRedirectUri())) {
            duplicatedField.add("RedirectUri");
        }

        if (!duplicatedField.isEmpty()) {
            throw new DuplicatedDataException(duplicatedField);
        }

    }

    public void checkDuplicatedDataToUpdate(UUID id, Client client) {
        List<String> duplicatedField = new ArrayList<>();

        if (repository.findByClientId(client.getClientId()).isPresent() &&
                !repository.findByClientId(client.getClientId()).get().getId().equals(client.getId())) {
            duplicatedField.add("ClientId");
        }

        if (repository.findByRedirectUri(client.getRedirectUri()).isPresent() &&
                !repository.findByRedirectUri(client.getRedirectUri()).get().getId().equals(client.getId())) {
            duplicatedField.add("RedirectUri");
        }

        if (!duplicatedField.isEmpty()) {
            throw new DuplicatedDataException(duplicatedField);
        }

    }


}
