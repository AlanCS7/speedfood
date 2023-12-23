package io.github.alancs7.speedfood.domain.exception;

public class RestauranteNotFoundException extends ResourceNotFoundException {

    public RestauranteNotFoundException(String message) {
        super(message);
    }

    public RestauranteNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d", id));
    }
}
