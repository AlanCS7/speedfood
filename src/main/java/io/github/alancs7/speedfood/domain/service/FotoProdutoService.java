package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.model.FotoProduto;
import io.github.alancs7.speedfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        produtoRepository
                .findFotoById(produtoId, restauranteId)
                .ifPresent(fotoProduto -> produtoRepository.delete(fotoProduto));

        return produtoRepository.save(foto);
    }
}
