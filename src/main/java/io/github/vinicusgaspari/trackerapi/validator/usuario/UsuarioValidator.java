package io.github.vinicusgaspari.trackerapi.validator.usuario;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DuplicatedDataException;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public void verificarDadosDuplicadosAoAtualizar(UUID id, Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNome(usuario.getNome());

        if (usuarioEncontrado.isPresent()) {
            if (!usuarioEncontrado.get().getId().equals(id)) {
                throw new DuplicatedDataException(List.of("Nome"));
            }
        }
    }

    public Optional<Usuario> validarUsuarioPorNomeDuplicado(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if(usuario.isPresent()){
            throw new DuplicatedDataException(List.of("Nome"));
        }
            return usuario;
    }

    public Usuario validarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("USUARIO"));
    }

    public Usuario validarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("NOME"));
    }

}
