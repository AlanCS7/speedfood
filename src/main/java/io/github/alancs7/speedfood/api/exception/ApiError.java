package io.github.alancs7.speedfood.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ApiError {

    @Schema(description = "Status do erro", example = "400")
    private Integer status;

    @Schema(description = "Data e hora da requisição", example = "2024-04-12T04:28:57.2513487Z")
    private OffsetDateTime timestamp;

    @Schema(description = "Tipo do erro", example = "https://speedfood.com.br/dados-invalidos")
    private String type;

    @Schema(description = "Titulo do erro", example = "Dados inválidos")
    private String title;

    @Schema(description = "Detalhe do erro", example = "Um ou mais campos estão inválidos. Faça o preenchimento e tente novamente.")
    private String detail;

    @Schema(description = "Mensagem para o usuário", example = "Um ou mais campos estão inválidos. Faça o preenchimento e tente novamente.")
    private String userMessage;

    @Schema(description = "Lista de campos que geraram o erro (opcional)")
    private List<Field> fields;

    @Getter
    @Builder
    public static class Field {

        @Schema(description = "Nome do campo", example = "nome")
        private String name;

        @Schema(description = "Mensagem para o usuário", example = "Nome da cidade é obrigatório")
        private String userMessage;
    }
}
