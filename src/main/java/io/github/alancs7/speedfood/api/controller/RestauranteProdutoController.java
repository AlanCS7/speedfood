package io.github.alancs7.speedfood.api.controller;

import io.github.alancs7.speedfood.api.mapper.ProdutoMapper;
import io.github.alancs7.speedfood.api.model.dto.ProdutoDto;
import io.github.alancs7.speedfood.api.model.input.ProdutoInput;
import io.github.alancs7.speedfood.domain.model.Produto;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import io.github.alancs7.speedfood.domain.service.ProdutoService;
import io.github.alancs7.speedfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @GetMapping
    public List<ProdutoDto> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> produtos = produtoService.findByRestaurante(restaurante);

        return produtoMapper.toCollectionDto(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDto buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        return produtoMapper.toDto(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoMapper.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        return produtoMapper.toDto(produtoService.salvar(produto));
    }

    @PutMapping("/{produtoId}")
    public ProdutoDto atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoMapper.copyToDomainObject(produtoInput, produtoAtual);

        return produtoMapper.toDto(produtoService.salvar(produtoAtual));
    }
}