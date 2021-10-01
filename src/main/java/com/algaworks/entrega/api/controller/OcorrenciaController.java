package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.model.Ocorrencia;
import com.algaworks.entrega.api.domain.service.BuscaEntregaService;
import com.algaworks.entrega.api.domain.service.RegistroOcorrenciaService;
import com.algaworks.entrega.api.mapper.OcorrenciaMapper;
import com.algaworks.entrega.api.model.request.OcorrenciaRequest;
import com.algaworks.entrega.api.model.response.OcorrenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaMapper ocorrenciaMapper;
    private final BuscaEntregaService buscaEntregaService;
    private final RegistroOcorrenciaService registroOcorrenciaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaRequest.getDescricao());
        return ocorrenciaMapper.toResponse(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaResponse> listar(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        return ocorrenciaMapper.toListResponse(entrega.getOcorrencias());
    }
}

