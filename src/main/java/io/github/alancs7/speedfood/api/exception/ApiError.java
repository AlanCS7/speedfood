package io.github.alancs7.speedfood.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("ApiError")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class ApiError {

    @ApiModelProperty(value = "Status do erro", example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(value = "Data e hora da requisição", example = "2024-04-12T04:28:57.2513487Z", position = 5)
    private OffsetDateTime timestamp;

    @ApiModelProperty(value = "Tipo do erro", example = "https://speedfood.com.br/dados-invalidos", position = 10)
    private String type;

    @ApiModelProperty(value = "Titulo do erro", example = "Dados inválidos", position = 15)
    private String title;

    @ApiModelProperty(value = "Detalhe do erro", example = "Um ou mais campos estão inválidos. Faça o preenchimento e tente novamente.", position = 20)
    private String detail;

    @ApiModelProperty(value = "Mensagem para o usuário", example = "Um ou mais campos estão inválidos. Faça o preenchimento e tente novamente.", position = 25)
    private String userMessage;

    @ApiModelProperty(value = "Lista de campos que geraram o erro (opcional)", position = 30)
    private List<Field> fields;

    @ApiModel("Field")
    @Getter
    @Builder
    public static class Field {

        @ApiModelProperty(value = "Nome do campo", example = "nome")
        private String name;

        @ApiModelProperty(value = "Mensagem para o usuário", example = "Nome da cidade é obrigatório")
        private String userMessage;
    }
}
