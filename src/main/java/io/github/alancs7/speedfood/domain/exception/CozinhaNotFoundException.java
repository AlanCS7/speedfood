package io.github.alancs7.speedfood.domain.exception;

public class CozinhaNotFoundException extends ResourceNotFoundException {

    public CozinhaNotFoundException(String message) {
        super(message);
    }

    public CozinhaNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de cozinha com código %d", id));
    }
}
