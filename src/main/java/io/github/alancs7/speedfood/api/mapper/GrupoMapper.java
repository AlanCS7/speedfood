package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.GrupoDto;
import io.github.alancs7.speedfood.api.model.input.GrupoInput;
import io.github.alancs7.speedfood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoDto toDto(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDto.class);
    }

    public List<GrupoDto> toCollectionDto(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Grupo toDomainObject(GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInput grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }
}
