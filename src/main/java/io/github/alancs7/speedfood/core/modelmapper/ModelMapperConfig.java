package io.github.alancs7.speedfood.core.modelmapper;

import io.github.alancs7.speedfood.api.model.dto.EnderecoDto;
import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.model.Endereco;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(RestauranteInput.class, Restaurante.class)
                .<Long>addMapping(RestauranteInput::getCozinha, (restauranteDest, value) -> restauranteDest.getCozinha().setId(value));

        modelMapper.createTypeMap(CidadeInput.class, Cidade.class)
                .<Long>addMapping(CidadeInput::getEstado, (cidadeDest, value) -> cidadeDest.getEstado().setId(value));

        modelMapper.createTypeMap(Endereco.class, EnderecoDto.class)
                .<String>addMapping(
                        enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                        (enderecoDtoDest, value) -> enderecoDtoDest.getCidade().setEstado(value));

        return modelMapper;
    }
}
