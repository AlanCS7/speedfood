package io.github.alancs7.speedfood.api.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StandardError {

    private LocalDateTime dataHora;
    private String mensagem;
}
