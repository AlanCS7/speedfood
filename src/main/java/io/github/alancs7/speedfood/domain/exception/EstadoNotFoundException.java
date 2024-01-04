package io.github.alancs7.speedfood.domain.exception;

public class EstadoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public EstadoNotFoundException(String message) {
        super(message);
    }

    public EstadoNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de estado com código %d", id));
    }
}
