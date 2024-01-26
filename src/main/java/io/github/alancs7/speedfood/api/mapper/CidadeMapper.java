package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.CidadeDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDto toDto(Cidade cidade) {
        return modelMapper.map(cidade, CidadeDto.class);
    }

    public List<CidadeDto> toCollectionDto(List<Cidade> cidades) {
        return cidades.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        // para evitar org.springframework.orm.jpa.JpaSystemException: identifier of an instance of
        // io.github.alancs7.speedfood.domain.model.Estado was altered from 1 to 2
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }
}
