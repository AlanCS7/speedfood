package io.github.alancs7.speedfood.core.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

    @Schema(description = "Numero da página (começa em 0)", example = "0")
    private int page;

    @Schema(description = "Quantidade de elementos por página", example = "10")
    private int size;

    @Schema(description = "Nome da propriedade para ordenação", example = "nome,asc")
    private List<String> sort;

}
