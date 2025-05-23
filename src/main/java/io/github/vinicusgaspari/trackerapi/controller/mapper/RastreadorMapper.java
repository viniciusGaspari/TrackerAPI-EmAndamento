package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador.RastreadorRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.rastreador.RastreadorResponse;
import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RastreadorMapper {

    Rastreador toEntity(RastreadorRequest dto);

    RastreadorResponse toDTO(Rastreador entity);

    List<RastreadorResponse> toListDTO(List<Rastreador> entityList);

    List<Rastreador> toListEntity(List<Rastreador> dtoList);

}
