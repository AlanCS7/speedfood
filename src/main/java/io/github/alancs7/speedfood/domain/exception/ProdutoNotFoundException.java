package io.github.alancs7.speedfood.domain.exception;

public class ProdutoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de produto com código %s para o restaurante de código %s",
                produtoId, restauranteId));
    }
}
