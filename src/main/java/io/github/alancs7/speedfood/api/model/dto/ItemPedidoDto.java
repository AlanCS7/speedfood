package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoDto {

    @Schema(description = "ID do produto", example = "1")
    private Long produtoId;

    @Schema(description = "Nome do produto", example = "Bife Ancho")
    private String produtoNome;

    @Schema(description = "Quantidade do item", example = "1")
    private Integer quantidade;

    @Schema(description = "Preço unitário do item", example = "79")
    private BigDecimal precoUnitario;

    @Schema(description = "Preço total do item", example = "79")
    private BigDecimal precoTotal;

    @Schema(description = "Observação do item", example = "Ao ponto")
    private String observacao;

}
