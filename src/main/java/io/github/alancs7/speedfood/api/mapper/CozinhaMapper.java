package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.CozinhaDto;
import io.github.alancs7.speedfood.api.model.input.CozinhaInput;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CozinhaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDto toDto(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDto.class);
    }

    public List<CozinhaDto> toCollectionDto(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(this::toDto)
                .toList();
    }

    public Cozinha toDomainObject(CozinhaInput cozinhaInput) {
        return modelMapper.map(cozinhaInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput cozinhaInput, Cozinha cozinha) {
        modelMapper.map(cozinhaInput, cozinha);
    }
}
