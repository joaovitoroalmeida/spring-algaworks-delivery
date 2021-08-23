package com.algaworks.entrega.api.controller.exception.handler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
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
                .dataHora(LocalDateTime.now())
                .titulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
                .campos(campos)
                .build();

        return super.handleExceptionInternal(exception, mensagemErro, headers, status, request);
    }
}
