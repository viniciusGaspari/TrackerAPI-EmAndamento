package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import io.github.vinicusgaspari.trackerapi.repository.UsuarioRepository;
import io.github.vinicusgaspari.trackerapi.validator.UsuarioAutenticado;
import io.github.vinicusgaspari.trackerapi.validator.ValidarAcessoUsuario;
import io.github.vinicusgaspari.trackerapi.validator.contrato.ContratoValidator;
import io.github.vinicusgaspari.trackerapi.validator.operadora.OperadoraValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RastreadorService {

    private final RastreadorRepository rastreadorRepository;
    private final ContratoValidator validarQuantidade;
    private final UsuarioRepository usuarioRepository;
    private final ValidarAcessoUsuario validarAcessoUsuario;
    private final OperadoraValidator operadoraValidator;

    private final static UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();

    public Rastreador buscarPorId(UUID id) {
        return validarAcessoUsuario.isAcessoValidoRasteador(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
    }

    public Rastreador salvar(Rastreador rastreador) {
        Usuario usuario = validarAcessoUsuario.isAcessoValidoUsuario(rastreador.getUsuario().getId(), usernameContaAutenticado.obtendoUsuarioAutenticado());
        validarQuantidade.verificarQuantidadeRastreadorContrato(rastreador.getUsuario(), usuario.getContrato());
        rastreador.setUsuario(usuario);
        rastreador.setOperadora(operadoraValidator.validarOperadoraPorId(rastreador.getOperadora().getId()));
        return rastreadorRepository.save(rastreador);
    }

    public List<Rastreador> buscarPorList(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LISTA DE USUARIOS"));
        return rastreadorRepository.findByUsuario(usuario);
    }

    public void deletarPorId(UUID id) {
        rastreadorRepository.delete(rastreadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RASTREADOR")));
    }
}
