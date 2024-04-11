package io.github.alancs7.speedfood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @ApiModelProperty(value = "Nome da cidade", example = "Campinas")
    @NotBlank
    private String nome;

    @ApiModelProperty(value = "ID do estado", example = "2")
    @NotNull
    private Long estado;
}
