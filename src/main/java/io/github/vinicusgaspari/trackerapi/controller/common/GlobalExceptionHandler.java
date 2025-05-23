package io.github.vinicusgaspari.trackerapi.controller.common;

import io.github.vinicusgaspari.trackerapi.controller.exceptions.AcessoNegadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.DadoDuplicadoException;
import io.github.vinicusgaspari.trackerapi.controller.exceptions.ContratoException;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.exceptions.ErroRespostaResponse;
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
    public ErroRespostaResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.info("Resposta inválida");
        List<String> fieldErrorList = ex.getFieldErrors()
                .stream()
                .map(FieldError::getField) // Extrai diretamente o nome do campo
                .toList();

        return new ErroRespostaResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Campos obrigatório",
                fieldErrorList
        );

    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroRespostaResponse entityNotFoundException(EntityNotFoundException ex) {
        return new ErroRespostaResponse(
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada: " + ex.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroRespostaResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.info("ID inválido");
        return new ErroRespostaResponse(
                HttpStatus.BAD_REQUEST.value(),
                "ID incorreto",
                List.of()
        );
    }

    @ExceptionHandler(DadoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroRespostaResponse duplicatedDataException(DadoDuplicadoException ex) {
        return new ErroRespostaResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMensagem(),
                ex.getDadosDuplicados()
        );
    }

    @ExceptionHandler(ContratoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroRespostaResponse quantidadeLimiteContratoException(ContratoException ex) {
        List<String> rastreadoresString = ex.getRastreadores()
                .stream()
                .map(r -> UUID.fromString(String.valueOf(r.id())).toString()) // Corrigindo acesso ao ID
                .toList();
        return new ErroRespostaResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMensagem(),
                rastreadoresString
        );
    }

    @ExceptionHandler(AcessoNegadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroRespostaResponse acessoNegadoException(AcessoNegadoException ex) {
        return new ErroRespostaResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMensagem(),
                List.of()
        );
    }

}
