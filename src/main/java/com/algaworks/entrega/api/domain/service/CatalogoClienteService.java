package com.algaworks.entrega.api.domain.service;

import com.algaworks.entrega.api.domain.exception.NegocioException;
import com.algaworks.entrega.api.domain.model.Cliente;
import com.algaworks.entrega.api.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CatalogoClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){

        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if(emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
