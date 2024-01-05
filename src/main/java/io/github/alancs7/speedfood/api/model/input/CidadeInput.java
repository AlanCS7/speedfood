package io.github.alancs7.speedfood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInput {

    @NotBlank
    private String nome;

    @NotNull
    private Long estado;
}
