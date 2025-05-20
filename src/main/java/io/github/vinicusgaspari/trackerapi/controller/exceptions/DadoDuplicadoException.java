package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DadoDuplicadoException extends RuntimeException {

    @Getter
    String mensagem;
    @Getter
    List<String> duplicatedField = new ArrayList<>();

    public DadoDuplicadoException(List<String> duplicatedFields) {
        this.duplicatedField = duplicatedFields;
        this.mensagem = "Dados duplicados";
    }
}
