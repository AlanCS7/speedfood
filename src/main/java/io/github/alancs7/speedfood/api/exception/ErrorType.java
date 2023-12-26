package io.github.alancs7.speedfood.api.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    SERVER_ERROR("/erro-de-sistema", "Erro de sistema"),
    INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
    INCOMPREHENSIBLE_MESSAGE("/mensagem-incompreensível", "Mensagem incompreensível"),
    RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
    RESOURCE_IN_USE("/recurso-em-uso", "Recurso em uso"),
    BUSINESS_ERROR("/erro-negocio", "Violação de regra de negócio");

    private final String title;
    private final String uri;

    ErrorType(String path, String title) {
        this.uri = "https://speedfood.com.br" + path;
        this.title = title;
    }
}
