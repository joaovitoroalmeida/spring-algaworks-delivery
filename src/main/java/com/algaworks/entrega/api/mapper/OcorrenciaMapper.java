package com.algaworks.entrega.api.mapper;

import com.algaworks.entrega.api.domain.model.Ocorrencia;
import com.algaworks.entrega.api.model.response.OcorrenciaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OcorrenciaMapper {

    private final ModelMapper modelMapper;

    public OcorrenciaResponse toResponse(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia, OcorrenciaResponse.class);
    }

    public List<OcorrenciaResponse> toListResponse(List<Ocorrencia> ocorrencia){
        return ocorrencia
                    .stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
    }
}
