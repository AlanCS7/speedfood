package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDto {

    @Schema(description = "ID da forma de pagamento", example = "1")
    private Long id;

    @Schema(description = "Descrição da forma de pagamento", example = "Cartão de crédito")
    private String descricao;
}
