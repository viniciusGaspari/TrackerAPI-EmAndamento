package io.github.vinicusgaspari.trackerapi.validator.usuario;

import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.validator.security.UsuarioAutenticado;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UsuarioSpecs {

    private final static UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();

    public static Specification<Usuario> isIdEqual(UUID id) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("id"), id),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isNomeLike(String nome) {
        return (root, query, cb) -> cb.and(
                cb.like(root.get("nome"), "%" + nome + "%"),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isCidadeLike(String cidade) {
        return (root, query, cb) -> cb.and(
                cb.like(root.get("cidade"), "%" + cidade + "%"),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isContratoLike(String contrato) {
        return (root, query, cb) -> cb.and(
                cb.like(root.get("contrato").get("nome"), "%" + contrato + "%"),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isRoleLike(String role) {
        return (root, query, cb) -> cb.and(
                cb.like(root.get("role").get("nome"), "%" + role + "%"),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isDataCadastroAno(Integer dataCadastro) {
        return (root, query, cb) -> cb.and(
                cb.equal(cb.function("to_char", String.class,
                        root.get("dataCadastro"), cb.literal("YYYY")), dataCadastro.toString()),
                cb.equal(root.get("conta").get("username"), usernameContaAutenticado.obterUsernameUsuarioAutenticado())
        );
    }

    public static Specification<Usuario> isNomeLogadoEqual(String nome) {
        return (root, query, cb) ->
                cb.equal(root.get("conta").get("nome"), nome);
    }

}
