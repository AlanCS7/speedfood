package io.github.alancs7.speedfood.domain.exception;

public abstract class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
