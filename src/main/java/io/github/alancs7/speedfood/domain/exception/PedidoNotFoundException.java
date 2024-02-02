package io.github.alancs7.speedfood.domain.exception;

public class PedidoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public PedidoNotFoundException(String codigoPedido) {
        super(String.format("Não existe um cadastro de pedido com código %s", codigoPedido));
    }

}
