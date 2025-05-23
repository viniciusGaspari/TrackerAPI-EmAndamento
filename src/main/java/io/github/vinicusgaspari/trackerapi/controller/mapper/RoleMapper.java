package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.role.RoleRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.role.RoleResponse;
import io.github.vinicusgaspari.trackerapi.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequest dto);

    RoleResponse toDTO(Role entity);

}
