package io.github.alancs7.speedfood.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDto {

    @Schema(description = "Nome da foto do produto", example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
    private String nomeArquivo;

    @Schema(description = "Descrição da foto do produto", example = "Prime Rib ao ponto")
    private String descricao;

    @Schema(description = "Content-Type da foto do produto", example = "image/png")
    private String contentType;

    @Schema(description = "Tamanho da foto do produto", example = "202912")
    private Long tamanho;

}
