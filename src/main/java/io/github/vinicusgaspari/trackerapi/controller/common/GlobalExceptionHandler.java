package io.github.vinicusgaspari.trackerapi.controller.common;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.QuantidadeContratoException;
import io.github.vinicusgaspari.trackerapi.controller.response.ErroResposta;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.info("Resposta inválida");
        List<String> fieldErrorList = ex.getFieldErrors()
                .stream()
                .map(FieldError::getField) // Extrai diretamente o nome do campo
                .toList();

        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Campos obrigatório",
                fieldErrorList
        );

    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta entityNotFoundException(EntityNotFoundException ex) {
        return new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada: " + ex.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.info("ID inválido");
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "ID incorreto",
                List.of()
        );
    }

    @ExceptionHandler(DadoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta duplicatedDataException(DadoDuplicadoException ex) {
        return new ErroResposta(
                HttpStatus.CONFLICT.value(),
                ex.getMensagem(),
                ex.getDuplicatedField()
        );
    }

    @ExceptionHandler(QuantidadeContratoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta quantidadeLimiteContratoException(QuantidadeContratoException ex) {
        List<String> rastreadoresString = ex.getRastreadores()
                .stream()
                .map(r -> UUID.fromString(String.valueOf(r.id())).toString()) // Corrigindo acesso ao ID
                .toList();
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMensagem(),
                rastreadoresString
        );
    }

    @ExceptionHandler(AcessoNegadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta acessoNegadoException(AcessoNegadoException ex) {
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                List.of()
        );
    }

}
