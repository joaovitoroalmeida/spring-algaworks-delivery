package com.algaworks.entrega.api.controller;

import com.algaworks.entrega.api.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("clientes")
    public List<Cliente> listar(){

        var cliente1 = Cliente.builder()
                .id(1L)
                .nome("Joao")
                .telefone("4321341231")
                .email("joao@gmail.com")
                .build();

        var cliente2 = Cliente.builder()
                .id(1L)
                .nome("Joao")
                .telefone("4321341231")
                .email("joao@gmail.com")
                .build();

        return List.of(cliente1, cliente2);
    }

}
