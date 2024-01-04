package io.github.alancs7.speedfood.domain.exception;

public class RestauranteNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestauranteNotFoundException(String message) {
        super(message);
    }

    public RestauranteNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d", id));
    }
}
