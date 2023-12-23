package io.github.alancs7.speedfood.domain.exception;

public class CidadeNotFoundException extends ResourceNotFoundException {

    public CidadeNotFoundException(String message) {
        super(message);
    }

    public CidadeNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de cidade com código %d", id));
    }
}
