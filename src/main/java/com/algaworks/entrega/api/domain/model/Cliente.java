package com.algaworks.entrega.api.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
