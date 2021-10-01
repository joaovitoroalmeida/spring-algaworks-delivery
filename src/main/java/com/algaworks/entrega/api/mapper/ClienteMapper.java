package com.algaworks.entrega.api.mapper;

import com.algaworks.entrega.api.domain.model.Cliente;
import com.algaworks.entrega.api.model.request.ClienteRequest;
import com.algaworks.entrega.api.model.response.ClienteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteResponse toResponse(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public Cliente toEntity(ClienteRequest clienteRequest){
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public List<ClienteResponse> toListResponse(List<Cliente> clientes){
        return clientes
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
