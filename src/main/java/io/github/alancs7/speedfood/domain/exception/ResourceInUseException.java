package io.github.alancs7.speedfood.domain.exception;

public class ResourceInUseException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ResourceInUseException(String message) {
        super(message);
    }
}
