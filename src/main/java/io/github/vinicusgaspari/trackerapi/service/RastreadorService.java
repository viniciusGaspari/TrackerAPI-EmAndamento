package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.RastreadorRepository;
import io.github.vinicusgaspari.trackerapi.validator.contrato.ContratoValidator;
import io.github.vinicusgaspari.trackerapi.validator.operadora.OperadoraValidator;
import io.github.vinicusgaspari.trackerapi.validator.rastreador.RastreadorValidator;
import io.github.vinicusgaspari.trackerapi.validator.security.UsuarioAutenticado;
import io.github.vinicusgaspari.trackerapi.validator.security.ValidarAcessoUsuario;
import io.github.vinicusgaspari.trackerapi.validator.usuario.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RastreadorService {

    private final RastreadorRepository rastreadorRepository;
    private final UsuarioValidator usuarioValidator;
    private final ContratoValidator contratoValidator;
    private final ValidarAcessoUsuario validarAcessoUsuario;
    private final OperadoraValidator operadoraValidator;
    private final RastreadorValidator rastreadorValidator;

    private final static UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();

    public Rastreador buscarPorId(UUID id) {
        return validarAcessoUsuario.isAcessoValidoRasteador(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
    }

    public Rastreador salvar(Rastreador rastreador) {
        Usuario usuario = validarAcessoUsuario.isAcessoValidoUsuario(rastreador.getUsuario().getId(), usernameContaAutenticado.obtendoUsuarioAutenticado());
        contratoValidator.verificarQuantidadeRastreadorContrato(rastreador.getUsuario(), usuario.getContrato());
        rastreador.setNome(rastreadorValidator.validarRastreadorPorNome(rastreador.getNome()));
        rastreador.setUsuario(usuarioValidator.validarUsuarioPorId(rastreador.getUsuario().getId()));
        rastreador.setOperadora(operadoraValidator.validarOperadoraPorId(rastreador.getOperadora().getId()));
        return rastreadorRepository.save(rastreador);
    }

    public List<Rastreador> buscarPorList(UUID id) {
        return rastreadorRepository.findByUsuario(validarAcessoUsuario.isAcessoValidoUsuario(id, usernameContaAutenticado.obtendoUsuarioAutenticado()));
    }

    public void deletarPorId(UUID id) {
        rastreadorRepository.delete(validarAcessoUsuario.isAcessoValidoRasteador(id, usernameContaAutenticado.obtendoUsuarioAutenticado()));
    }

    public Rastreador atualizarPorId(UUID id, Rastreador rastreador) {
        Rastreador rastreadorEncontrado = validarAcessoUsuario.isAcessoValidoRasteador(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
        rastreadorEncontrado.setUsuario(usuarioValidator.validarUsuarioPorId(rastreador.getUsuario().getId()));
        rastreadorEncontrado.setNome(rastreadorValidator.validarNomeUsuarioExistente(rastreador.getNome(), id));
        rastreadorEncontrado.setOperadora(operadoraValidator.validarOperadoraPorId(rastreador.getOperadora().getId()));
        return rastreadorRepository.save(rastreadorEncontrado);
    }

}
