package io.github.vinicusgaspari.trackerapi.validator.rastreador;

import io.github.vinicusgaspari.trackerapi.model.Rastreador;
import io.github.vinicusgaspari.trackerapi.validator.security.UsuarioAutenticado;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class RastreadorSpecs {

    final static UsuarioAutenticado usernameContaAutenticado = new UsuarioAutenticado();

    public static Specification<Rastreador> isIdEqual(UUID id) {
        return (root, query, cb) ->
                cb.equal(root.get("id"), id);
    }

    public static Specification<Rastreador> isNomeLike(String nome) {
        return (root, query, cb) ->
                cb.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Rastreador> isUsuarioEqual(UUID usuario) {
        return (root, query, cb) ->
                cb.equal(root.get("usuario").get("id"), usuario);
    }

    public static Specification<Rastreador> isOperadoraEqual(UUID operadora) {
        return (root, query, cb) ->
                cb.equal(root.get("operadora").get("id"), operadora);
    }

}
