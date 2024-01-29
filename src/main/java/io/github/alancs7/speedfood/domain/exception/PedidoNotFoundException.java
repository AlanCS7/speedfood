package io.github.alancs7.speedfood.domain.exception;

public class PedidoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public PedidoNotFoundException(String message) {
        super(message);
    }

    public PedidoNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de pedido com código %d", id));
    }
}
