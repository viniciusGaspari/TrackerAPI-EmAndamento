package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DadoDuplicadoException extends RuntimeException {

    @Getter
    List<String> dadosDuplicados = new ArrayList<>();
    @Getter
    String mensagem;

    public DadoDuplicadoException(List<String> dadosDuplicados) {
        this.mensagem = "Dados duplicados";
        this.dadosDuplicados = dadosDuplicados;
    }
}
