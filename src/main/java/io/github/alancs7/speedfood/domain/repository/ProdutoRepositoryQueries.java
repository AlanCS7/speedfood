package io.github.alancs7.speedfood.domain.repository;

import io.github.alancs7.speedfood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto saveFoto(FotoProduto foto);

    void deleteFoto(FotoProduto foto);
}
