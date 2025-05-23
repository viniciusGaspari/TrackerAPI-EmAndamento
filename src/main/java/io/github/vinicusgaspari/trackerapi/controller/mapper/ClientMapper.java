package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.client.ClientRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.client.ClientResponse;
import io.github.vinicusgaspari.trackerapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequest dto);

    ClientResponse toDTO(Client client);

}
