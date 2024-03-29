package io.github.alancs7.speedfood.core.modelmapper;

import io.github.alancs7.speedfood.api.model.dto.EnderecoDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.api.model.input.ItemPedidoInput;
import io.github.alancs7.speedfood.api.model.input.PedidoInput;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.github.alancs7.speedfood.domain.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(RestauranteInput.class, Restaurante.class)
                .<Long>addMapping(
                        RestauranteInput::getCozinha,
                        (restauranteDest, value) -> restauranteDest.getCozinha().setId(value))
                .<Long>addMapping(
                        restauranteSrc -> restauranteSrc.getEndereco().getCidade(),
                        (restauranteDest, value) -> restauranteDest.getEndereco().getCidade().setId(value));

        modelMapper.createTypeMap(CidadeInput.class, Cidade.class)
                .<Long>addMapping(
                        CidadeInput::getEstado,
                        (cidadeDest, value) -> cidadeDest.getEstado().setId(value));

        modelMapper.createTypeMap(Endereco.class, EnderecoDto.class)
                .<String>addMapping(
                        enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                        (enderecoDtoDest, value) -> enderecoDtoDest.getCidade().setEstado(value));

        modelMapper.createTypeMap(PedidoInput.class, Pedido.class)
                .<Long>addMapping(
                        PedidoInput::getRestaurante,
                        (pedidoDest, value) -> pedidoDest.getRestaurante().setId(value))
                .<Long>addMapping(
                        PedidoInput::getFormaPagamento,
                        (pedidoDest, value) -> pedidoDest.getFormaPagamento().setId(value))
                .<Long>addMapping(
                        pedidoSrc -> pedidoSrc.getEnderecoEntrega().getCidade(),
                        (pedidoDest, value) -> pedidoDest.getEnderecoEntrega().getCidade().setId(value)
                );

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
