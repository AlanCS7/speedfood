package io.github.alancs7.speedfood.api.model.input;

import io.github.alancs7.speedfood.core.validation.annotation.FileContentType;
import io.github.alancs7.speedfood.core.validation.annotation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @ApiModelProperty(hidden = true)
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;

    @Schema(description = "Descrição da foto do produto", example = "Prime Rib ao ponto", required = true)
    @NotBlank
    private String descricao;

}
