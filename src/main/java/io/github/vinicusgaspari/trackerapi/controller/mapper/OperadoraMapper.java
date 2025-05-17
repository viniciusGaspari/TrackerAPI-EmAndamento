package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.OperadoraResponse;
import io.github.vinicusgaspari.trackerapi.model.Operadora;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperadoraMapper {

    Operadora toEntity(OperadoraResponse dto);

    OperadoraResponse toDTO(Operadora entity);

}
