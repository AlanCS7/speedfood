package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.PedidoResumoDto;
import io.github.alancs7.speedfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoMapper {

    @Autowired
    private ModelMapper modelMapper;


    public PedidoResumoDto toDto(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoDto.class);
    }

    public List<PedidoResumoDto> toCollectionDto(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
