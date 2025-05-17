package io.github.vinicusgaspari.trackerapi.validator.client;

import io.github.vinicusgaspari.trackerapi.model.Client;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ClientSpecs {

    public static Specification<Client> isIdEqual(UUID id){
        return (root, query, cb) ->
                cb.equal(root.get("id"), id);
    }

    public static Specification<Client> isClientIdLike(String clientId){
        return (root, query, cb) ->
            cb.like(root.get("clientId"), "%" + clientId + "%");
    }

    public static Specification<Client> isRedirectUriLike(String redirectUri){
        return (root, query, cb) ->
                cb.like(root.get("redirectUri"), "%" + redirectUri + "%");
    }

    public static Specification<Client> isScopeLike(String scope){
        return (root, query, cb) ->
            cb.like(root.get("scope"), "%" + scope + "%");
    }
}
