package io.github.vinicusgaspari.trackerapi.validator.usuario;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
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

    public String verificarDadosDuplicadosAoAtualizar(UUID id, Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByNome(usuario.getNome());

        if (usuarioEncontrado.isPresent()) {
            if (!usuarioEncontrado.get().getId().equals(id)) {
                throw new DadoDuplicadoException(List.of("NOME DO USUARIO"));
            }
        }
        return usuario.getNome();
    }

    public String validarUsuarioPorNomeDuplicado(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if(usuario.isPresent()){
            throw new DadoDuplicadoException(List.of("NOME DO USUARIO"));
        }
        return nome;
    }

    public Usuario validarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID DO USUARIO"));
    }

    public Usuario validarUsuarioPorNome(String nome) {
        return usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("NOME DO USUARIO"));
    }

}
