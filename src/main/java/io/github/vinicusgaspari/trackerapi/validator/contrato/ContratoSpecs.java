package io.github.vinicusgaspari.trackerapi.validator.contrato;

import io.github.vinicusgaspari.trackerapi.model.Contrato;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.UUID;

public class ContratoSpecs {

    public static Specification<Contrato> isIdEqual(UUID id) {
        return (root, query, cb) ->
                cb.equal(root.get("id"), id);
    }

    public static Specification<Contrato> isNomeLike(String nome) {
        return (root, query, cb) ->
                cb.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<Contrato> isPrecoLike(BigDecimal preco) {
        return (root, query, cb) ->
                cb.like(root.get("preco"), "%" + preco + "%");
    }

    public static Specification<Contrato> isQuantidadeEqual(Integer quantidade) {
        return (root, query, cb) ->
                cb.equal(root.get("quantidade"), quantidade);
    }

}
