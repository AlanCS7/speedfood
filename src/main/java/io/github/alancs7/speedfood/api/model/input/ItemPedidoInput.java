package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemPedidoInput {

    @Schema(description = "ID do produto", example = "1")
    @NotNull
    private Long produtoId;

    @Schema(description = "Quantidade do item", example = "1")
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @Schema(description = "Observação do item", example = "Ao ponto")
    private String observacao;

}
