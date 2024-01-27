package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.ProdutoDto;
import io.github.alancs7.speedfood.api.model.input.ProdutoInput;
import io.github.alancs7.speedfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDto toDto(Produto produto) {
        return modelMapper.map(produto, ProdutoDto.class);
    }

    public List<ProdutoDto> toCollectionDto(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Produto toDomainObject(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}
