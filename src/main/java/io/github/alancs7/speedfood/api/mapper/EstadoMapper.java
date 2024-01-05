package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.EstadoDto;
import io.github.alancs7.speedfood.api.model.input.EstadoInput;
import io.github.alancs7.speedfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDto toDto(Estado estado) {
        return modelMapper.map(estado, EstadoDto.class);
    }

    public List<EstadoDto> toCollectionDto(List<Estado> estados) {
        return estados.stream()
                .map(this::toDto)
                .toList();
    }

    public Estado toDomainObject(EstadoInput estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }


    public void copyToDomainObject(EstadoInput estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }
}
