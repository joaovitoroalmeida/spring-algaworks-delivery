package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.repository.EntregaRepository;
import com.algaworks.entrega.api.domain.service.SolicitacaoEntregaService;
import com.algaworks.entrega.api.model.DestinatarioResponse;
import com.algaworks.entrega.api.model.EntregaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("entregas")
public class EntregaController {

    private final EntregaRepository entregaRepository;
    private final SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega){
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entrega> listar(){
        return entregaRepository.findAll();
    }

    @GetMapping("{entregaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntregaResponse> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
                    EntregaResponse entregaResponse = new EntregaResponse();
                    entregaResponse.setId(entrega.getId());
                    entregaResponse.setStatusEntrega(entrega.getStatus());
                    entregaResponse.setTaxaEntrega(entrega.getTaxa());
                    entregaResponse.setDataFinalizacao(entrega.getDataFinalizacao());
                    entregaResponse.setDataPedido(entrega.getDataPedido());
                    entregaResponse.setNomeCliente(entrega.getCliente().getNome());
                    entregaResponse.setDataFinalizacao(entrega.getDataFinalizacao());
                    entregaResponse.setDestinatario(new DestinatarioResponse());
                    entregaResponse.getDestinatario().setNome(entrega.getDestinatario().getNome());
                    entregaResponse.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                    entregaResponse.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                    entregaResponse.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                    entregaResponse.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                    return ResponseEntity.ok(entregaResponse);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
