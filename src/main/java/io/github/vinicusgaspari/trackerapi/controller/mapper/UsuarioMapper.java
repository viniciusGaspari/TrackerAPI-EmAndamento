package io.github.vinicusgaspari.trackerapi.controller.mapper;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.PesquisasPorUsuarioResponse;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioRequest;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioResponse;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequest dto);

    UsuarioResponse toDTO(Usuario entity);

    PesquisasPorUsuarioResponse toPesquisaDTO(Usuario usuario);

}
