package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.FotoProdutoMapper;
import io.github.alancs7.speedfood.api.model.dto.FotoProdutoDto;
import io.github.alancs7.speedfood.api.model.input.FotoProdutoInput;
import io.github.alancs7.speedfood.domain.model.FotoProduto;
import io.github.alancs7.speedfood.domain.model.Produto;
import io.github.alancs7.speedfood.domain.service.FotoProdutoService;
import io.github.alancs7.speedfood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController {

    @Autowired
    private FotoProdutoService fotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoMapper fotoProdutoMapper;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDto atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @Valid FotoProdutoInput fotoProdutoInput) {

        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setNomeArquivo(arquivo.getOriginalFilename());
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());

        FotoProduto fotoProduto = fotoProdutoService.salvar(foto);

        return fotoProdutoMapper.toDto(fotoProduto);
    }
}
