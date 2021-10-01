package com.algaworks.entrega.api.mapper;

import com.algaworks.entrega.api.domain.model.Entrega;
import com.algaworks.entrega.api.model.request.EntregaRequest;
import com.algaworks.entrega.api.model.response.EntregaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntregaMapper {

    private final ModelMapper modelMapper;

    public EntregaResponse toResponse(Entrega entrega){
        return modelMapper.map(entrega, EntregaResponse.class);
    }

    public List<EntregaResponse> toListResponse(List<Entrega> entregas){
        return entregas
                    .stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequest entregaRequest){
        return modelMapper.map(entregaRequest, Entrega.class);
    }
}
