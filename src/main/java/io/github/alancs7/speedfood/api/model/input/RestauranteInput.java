package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteInput {

    @Schema(description = "Nome do restaurante", example = "Thai Gourmet")
    @NotBlank
    private String nome;

    @Schema(description = "Taxa de entrega do restaurante", example = "10")
    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Schema(description = "ID da cozinha", example = "1")
    @NotNull
    private Long cozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;
}
