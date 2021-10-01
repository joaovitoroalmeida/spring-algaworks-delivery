package com.algaworks.entrega.api.domain.service;

import com.algaworks.entrega.api.domain.model.Cliente;
import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.domain.model.StatusEntrega;
import com.algaworks.entrega.api.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        entrega.setCliente(cliente);
        return entregaRepository.save(entrega);
    }
}
