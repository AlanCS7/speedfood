package io.github.alancs7.speedfood.domain.exception;

public class GrupoNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public GrupoNotFoundException(String message) {
        super(message);
    }

    public GrupoNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de grupo com código %d", id));
    }
}
