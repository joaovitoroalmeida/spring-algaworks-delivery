package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Cliente;
import com.algaworks.entrega.api.domain.repository.ClienteRepository;
import com.algaworks.entrega.api.domain.service.CatalogoClienteService;
import com.algaworks.entrega.api.mapper.ClienteMapper;
import com.algaworks.entrega.api.model.request.ClienteRequest;
import com.algaworks.entrega.api.model.response.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;
    private final CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<ClienteResponse> listarClientes(){
        return clienteMapper.toListResponse(clienteRepository.findAll());
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> buscarClienteById(@PathVariable Long clienteId){

        return clienteRepository.findById(clienteId)
                .map(clienteMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criarCliente(@Valid @RequestBody ClienteRequest cliente){
        Cliente clienteEntityResponse = catalogoClienteService.salvar(clienteMapper.toEntity(cliente));
        return clienteMapper.toResponse(clienteEntityResponse);
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable Long clienteId, @Valid @RequestBody ClienteRequest cliente){
        return clienteRepository.findById(clienteId)
                .map(client -> {
                    Cliente clienteEntity = clienteMapper.toEntity(cliente);
                    clienteEntity.setId(client.getId());
                    return clienteMapper.toResponse(catalogoClienteService.salvar(clienteEntity));
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId){

        if (!clienteRepository.existsById(clienteId))
            return ResponseEntity.notFound().build();

        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }
}
