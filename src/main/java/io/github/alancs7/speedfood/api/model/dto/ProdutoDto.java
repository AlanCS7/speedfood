package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDto {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Porco com molho agridoce")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Deliciosa carne suína ao molho especial")
    private String descricao;

    @Schema(description = "Preço do produto", example = "78.90")
    private BigDecimal preco;

    @Schema(description = "Indica se o produto está ativo", example = "true")
    private Boolean ativo;
}
