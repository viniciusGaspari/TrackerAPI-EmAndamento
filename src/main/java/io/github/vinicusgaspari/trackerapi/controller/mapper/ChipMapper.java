package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.chip.ChipResponse;
import io.github.vinicusgaspari.trackerapi.model.Chip;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChipMapper {

    Chip toEntity(ChipRequest dto);

    ChipResponse toDTO(Chip entity);

}
