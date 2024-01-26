package io.github.alancs7.speedfood.domain.exception;

public class UsuarioNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de usuário com código %d", id));
    }
}
