package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta.ContaRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.conta.ContaResponse;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toEntity(ContaRequest dto);

    ContaResponse toDTO(Conta entity);

}
