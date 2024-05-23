package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @Schema(description = "Nome do produto", example = "Porco com molho agridoce")
    @NotBlank
    private String nome;

    @Schema(description = "Descrição do produto", example = "Deliciosa carne suína ao molho especial")
    @NotBlank
    private String descricao;

    @Schema(description = "Preço do produto", example = "78.90")
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @Schema(description = "Indica se o produto está ativo", example = "true")
    @NotNull
    private Boolean ativo;
}
