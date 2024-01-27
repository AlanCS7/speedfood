package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.FormaPagamentoDto;
import io.github.alancs7.speedfood.api.model.input.FormaPagamentoInput;
import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDto toDto(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDto.class);
    }

    public List<FormaPagamentoDto> toCollectionDto(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInput, formaPagamento);
    }
}
