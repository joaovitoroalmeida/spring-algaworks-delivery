package com.algaworks.entrega.api.domain.service;

import com.algaworks.entrega.api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscaEntregaService {

    private final EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
