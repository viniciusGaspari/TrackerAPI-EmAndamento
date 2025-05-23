package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.contrato.ContratoResponse;
import io.github.vinicusgaspari.trackerapi.model.Contrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    Contrato toEntity(ContratoRequest dto);

    ContratoResponse toDTO(Contrato entity);

}
