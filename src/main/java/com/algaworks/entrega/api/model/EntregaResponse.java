package com.algaworks.entrega.api.model;

import com.algaworks.entrega.api.domain.model.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EntregaResponse {

    private Long id;
    private String nomeCliente;
    private DestinatarioResponse destinatario;
    private BigDecimal taxaEntrega;
    private StatusEntrega statusEntrega;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
