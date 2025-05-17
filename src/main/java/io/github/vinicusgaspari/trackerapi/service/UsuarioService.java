package io.github.vinicusgaspari.trackerapi.service;

import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.repository.UsuarioRepository;
import io.github.vinicusgaspari.trackerapi.validator.UsuarioAutenticado;
import io.github.vinicusgaspari.trackerapi.validator.ValidarAcessoUsuario;
import io.github.vinicusgaspari.trackerapi.validator.contrato.ContratoValidator;
import io.github.vinicusgaspari.trackerapi.validator.usuario.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.github.vinicusgaspari.trackerapi.validator.usuario.UsuarioSpecs.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioValidator resolver;
    private final UsuarioRepository usuarioRepository;
    private final ContratoService contratoService;
    private final ContaService contaService;
    private final ContratoValidator validar;
    private final ValidarAcessoUsuario validarAcessoUsuario;
    private final UsuarioValidator usuarioValidator;

    private final static UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();

    public Usuario salvar(Usuario usuario) {
        usuarioValidator.validarUsuarioPorNomeDuplicado(usuario.getNome());
        usuario.setConta(contaService.buscarPorNome(usernameContaAutenticado.obtendoUsuarioAutenticado()));
        usuario.setContrato(contratoService.buscarPorNome(usuario.getContrato().getNome()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return validarAcessoUsuario.isAcessoValidoUsuario(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
    }

    public void deletar(UUID id) {
        Usuario usuario = validarAcessoUsuario.isAcessoValidoUsuario(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
        usuarioRepository.delete(usuario);
    }

    public Usuario atualizar(UUID id, Usuario usuarioResponse) {
        Usuario usuario = validarAcessoUsuario.isAcessoValidoUsuario(id, usernameContaAutenticado.obtendoUsuarioAutenticado());
        resolver.verificarDadosDuplicadosAoAtualizar(id, usuarioResponse);
        usuario.setNome(usuarioResponse.getNome());
        usuario.setCidade(usuarioResponse.getCidade());
        usuario.setContrato(contratoService.buscarPorNome(usuarioResponse.getContrato().getNome()));
        validar.verificarQuantidadeRastreadorContrato(usuario, usuario.getContrato());
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> buscarComFiltro(UUID id, String nome, String cidade, String contrato, String role, Integer dataCadastro, Integer pagina, Integer tamanhoPagina) {
        Specification<Usuario> specs = Specification.where((root, query, cb) -> cb.and(
                cb.conjunction(),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obtendoUsuarioAutenticado())
        ));
        if (id != null) {
            specs = specs.and(isIdEqual(id));
        }
        if (nome != null) {
            specs = specs.and(isNomeLike(nome));
        }
        if (cidade != null) {
            specs = specs.and(isCidadeLike(cidade));
        }
        if (contrato != null) {
            specs = specs.and(isContratoLike(contrato));
        }
        if (role != null) {
            specs = specs.and(isRoleLike(role));
        }
        if (dataCadastro != null) {
            specs = specs.and(isDataCadastroAno(dataCadastro));
        }
        return usuarioRepository.findAll(specs, PageRequest.of(pagina, tamanhoPagina));
    }
}