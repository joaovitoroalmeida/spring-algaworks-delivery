package com.algaworks.entrega.api.controller.exception.handler;

import com.algaworks.entrega.api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.entrega.api.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Campo> campos = exception.getBindingResult().getAllErrors()
                .stream()
                .map(error -> new Campo(((FieldError)error).getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());

        MensagemErro mensagemErro = MensagemErro.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
                .campos(campos)
                .build();

        return super.handleExceptionInternal(exception, mensagemErro, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        MensagemErro mensagemErro = MensagemErro.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(exception.getMessage())
                .build();

        return handleExceptionInternal(exception, mensagemErro, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        MensagemErro mensagemErro = MensagemErro.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(exception.getMessage())
                .build();

        return handleExceptionInternal(exception, mensagemErro, new HttpHeaders(), status, request);
    }
}
