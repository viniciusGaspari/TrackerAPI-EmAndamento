package io.github.vinicusgaspari.trackerapi.validator.conta;

import io.github.vinicusgaspari.trackerapi.model.Conta;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ContaSpecs {

    public static Specification<Conta> isIdEqual(UUID id) {
        return (root, query, cb) ->
                cb.equal(root.get("id"), id);
    }

    public static Specification<Conta> isUsernameLike(String username) {
        return (root, query, cb) ->
                cb.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<Conta> isRoleEqual(UUID idRole) {
        return (root, query, cb) ->
                cb.equal(root.get("operadora").get("id"), idRole);
    }

}
