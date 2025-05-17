package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.ContratoResponse;
import io.github.vinicusgaspari.trackerapi.model.Contrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    Contrato toEntity(ContratoResponse DTO);

    ContratoResponse toDTO(Contrato entity);

}
