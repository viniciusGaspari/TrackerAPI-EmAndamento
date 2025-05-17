package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.RoleResponse;
import io.github.vinicusgaspari.trackerapi.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleResponse dto);

    RoleResponse toDTO(Role entity);

}
