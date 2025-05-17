package io.github.vinicusgaspari.trackerapi.controller.common;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.DuplicatedDataException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.QuantidadeLimiteContratoException;
import io.github.vinicusgaspari.trackerapi.controller.response.ErroResposta;
import io.github.vinicusgaspari.trackerapi.controller.response.RastreadorResponse;
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

    @ExceptionHandler(DuplicatedDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta duplicatedDataException(DuplicatedDataException ex) {
        return new ErroResposta(
                HttpStatus.CONFLICT.value(),
                ex.getMensagem(),
                ex.getDuplicatedField()
        );
    }

    @ExceptionHandler(QuantidadeLimiteContratoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta quantidadeLimiteContratoException(QuantidadeLimiteContratoException ex) {
        List<String> rastreadoresString = ex.getRastreadores()
                .stream()
                .map(RastreadorResponse::nome)
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
