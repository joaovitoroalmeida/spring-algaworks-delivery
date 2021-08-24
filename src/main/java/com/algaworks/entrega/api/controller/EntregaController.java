package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.service.SolicitacaoEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("entregas")
public class EntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega){
        return solicitacaoEntregaService.solicitar(entrega);
    }
}
