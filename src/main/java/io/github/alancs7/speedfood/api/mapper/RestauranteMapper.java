package io.github.alancs7.speedfood.api.mapper;

import io.github.alancs7.speedfood.api.model.dto.RestauranteDto;
import io.github.alancs7.speedfood.api.model.input.RestauranteInput;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RestauranteDto toDto(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDto.class);
    }

    public List<RestauranteDto> toCollectionDto(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toDto)
                .toList();
    }

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
        // Para evitar org.springframework.orm.jpa.JpaSystemException: identifier of an instance of
        // io.github.alancs7.speedfood.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInput, restaurante);
    }

}
