package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import lombok.Getter;

public class AcessoNegadoException extends RuntimeException {
    @Getter
    String mensagem;
    public AcessoNegadoException(String entidade) {
        this.mensagem = "Conta não tem permissão ao: " + entidade;
    }
}
