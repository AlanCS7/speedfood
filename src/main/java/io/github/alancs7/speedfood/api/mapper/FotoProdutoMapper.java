package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.FotoProdutoDto;
import io.github.alancs7.speedfood.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDto toDto(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoDto.class);
    }

}
