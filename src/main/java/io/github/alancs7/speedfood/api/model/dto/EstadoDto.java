package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDto {

    @ApiModelProperty(value = "ID do estado", example = "2")
    private Long id;

    @ApiModelProperty(example = "São Paulo")
    private String nome;
}
