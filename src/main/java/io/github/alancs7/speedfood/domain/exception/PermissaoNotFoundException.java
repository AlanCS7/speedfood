package io.github.alancs7.speedfood.domain.exception;

public class PermissaoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public PermissaoNotFoundException(String message) {
        super(message);
    }

    public PermissaoNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de permissão com código %d", id));
    }
}
