package com.algaworks.entrega.api.exception.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MensagemErro {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;
}
