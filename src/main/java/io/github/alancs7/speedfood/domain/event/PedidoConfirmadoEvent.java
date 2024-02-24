package io.github.alancs7.speedfood.domain.event;

import io.github.alancs7.speedfood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;
}
