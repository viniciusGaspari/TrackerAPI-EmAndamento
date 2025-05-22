package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.ChipResponse;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChipMapper {

    Chip toEntity(ChipResponse dto);

    ChipResponse toDTO(Chip entity);

}
