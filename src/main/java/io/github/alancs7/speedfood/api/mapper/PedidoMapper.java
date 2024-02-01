package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.PedidoDto;
import io.github.alancs7.speedfood.api.model.input.PedidoInput;
import io.github.alancs7.speedfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDto toDto(Pedido pedido) {
        return modelMapper.map(pedido, PedidoDto.class);
    }

    public List<PedidoDto> toCollectionDto(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Pedido toDomainObject(PedidoInput pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
        modelMapper.map(pedidoInput, pedido);
    }
}
