package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.ContaResponse;
import io.github.vinicusgaspari.trackerapi.model.Conta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    Conta toEntity(ContaResponse dto);

    ContaResponse toDTO(Conta entity);

}
