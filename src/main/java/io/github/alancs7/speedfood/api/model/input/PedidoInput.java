package io.github.alancs7.speedfood.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @Schema(description = "ID do restaurante", example = "1")
    @NotNull
    private Long restaurante;

    @Schema(description = "ID da forma de pagamento", example = "1")
    @NotNull
    private Long formaPagamento;

    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens = new ArrayList<>();

}
