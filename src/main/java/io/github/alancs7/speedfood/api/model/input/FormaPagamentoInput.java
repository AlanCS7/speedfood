package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoInput {

    @NotBlank
    @Schema(description = "Descrição da forma de pagamento", example = "Cartão de crédito")
    private String descricao;
}
