package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.repository.EntregaRepository;
import com.algaworks.entrega.api.domain.service.FinalizacaoEntregaService;
import com.algaworks.entrega.api.domain.service.SolicitacaoEntregaService;
import com.algaworks.entrega.api.mapper.EntregaMapper;
import com.algaworks.entrega.api.model.request.EntregaRequest;
import com.algaworks.entrega.api.model.response.EntregaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final FinalizacaoEntregaService finalizacaoEntregaService;
    private final EntregaMapper entregaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse solicitar(@Valid @RequestBody EntregaRequest entregaRequest){

        Entrega entrega = entregaMapper.toEntity(entregaRequest);
        return entregaMapper.toResponse(solicitacaoEntregaService.solicitar(entrega));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntregaResponse> listar(){
        return entregaMapper.toListResponse(entregaRepository.findAll());
    }

    @GetMapping("{entregaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntregaResponse> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toResponse(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }
}
