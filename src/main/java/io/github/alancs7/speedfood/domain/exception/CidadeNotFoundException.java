package io.github.alancs7.speedfood.domain.exception;

public class CidadeNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public CidadeNotFoundException(String message) {
        super(message);
    }

    public CidadeNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de cidade com código %d", id));
    }
}
