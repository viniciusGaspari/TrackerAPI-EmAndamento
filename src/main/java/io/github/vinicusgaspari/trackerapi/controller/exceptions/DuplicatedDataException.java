package io.github.vinicusgaspari.trackerapi.controller.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DuplicatedDataException extends RuntimeException {

    @Getter
    String mensagem;
    @Getter
    List<String> duplicatedField = new ArrayList<>();

    public DuplicatedDataException(List<String> duplicatedFields) {
        this.duplicatedField = duplicatedFields;
        this.mensagem = "Dados duplicados";
    }
}
