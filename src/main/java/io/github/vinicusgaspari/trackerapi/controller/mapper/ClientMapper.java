package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.ClientResponse;
import io.github.vinicusgaspari.trackerapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientResponse dto);

    ClientResponse toDTO(Client client);

}
