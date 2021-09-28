package com.algaworks.entrega.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DestinatarioResponse {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
