package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.RastreadorResponse;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RastreadorMapper {

    Rastreador toEntity(RastreadorResponse dto);

    RastreadorResponse toDTO(Rastreador entity);

    List<RastreadorResponse> toListDTO(List<Rastreador> entityList);

    List<Rastreador> toListEntity(List<Rastreador> dtoList);

}
