package io.github.alancs7.speedfood.domain.exception;

public class FotoProdutoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNotFoundException(String message) {
        super(message);
    }

    public FotoProdutoNotFoundException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de foto do produto com código %s para o restaurante de código %s",
                produtoId, restauranteId));
    }
}
