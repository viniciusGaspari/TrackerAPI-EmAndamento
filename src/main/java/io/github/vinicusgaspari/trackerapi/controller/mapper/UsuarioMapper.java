package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.response.PesquisasPorUsuarioDTO;
import io.github.vinicusgaspari.trackerapi.controller.response.UsuarioResponse;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioResponse dto);

    UsuarioResponse toDTO(Usuario entity);

    PesquisasPorUsuarioDTO toPesquisaDTO(Usuario usuario);

}
