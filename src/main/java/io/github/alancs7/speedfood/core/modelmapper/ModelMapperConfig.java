package io.github.alancs7.speedfood.core.modelmapper;

import io.github.alancs7.speedfood.api.model.input.CidadeInput;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.github.alancs7.speedfood.domain.model.Cidade;
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
                .addMapping(RestauranteInput::getCozinha, (restaurante, value) -> restaurante.getCozinha().setId((Long) value));

        modelMapper.createTypeMap(CidadeInput.class, Cidade.class)
                .addMapping(CidadeInput::getEstado, (cidade, value) -> cidade.getEstado().setId((Long) value));

        return modelMapper;
    }
}
