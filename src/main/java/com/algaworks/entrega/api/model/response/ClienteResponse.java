package com.algaworks.entrega.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClienteResponse {

    private Long idCliente;
    private String nomeCliente;
    private String email;
    private String telefone;
}
