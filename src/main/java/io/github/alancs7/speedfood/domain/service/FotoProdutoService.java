package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.api.model.input.FotoProdutoInput;
import io.github.alancs7.speedfood.domain.model.FotoProduto;
import io.github.alancs7.speedfood.domain.model.Produto;
import io.github.alancs7.speedfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static io.github.alancs7.speedfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(Produto produto, FotoProdutoInput fotoProdutoInput) throws IOException {
        deleteFotoSeExistir(produto.getId());

        FotoProduto foto = criarFotoProduto(fotoProdutoInput, produto);

        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto
                .builder()
                .nomeArquivo(foto.getNomeArquivo())
                .inputStream(fotoProdutoInput.getArquivo().getInputStream())
                .build();

        fotoStorage.armazenar(novaFoto);

        return foto;
    }

    private void deleteFotoSeExistir(Long produtoId) {
        produtoRepository
                .findFotoById(produtoId)
                .ifPresent(produtoRepository::delete);
    }

    private FotoProduto criarFotoProduto(FotoProdutoInput fotoProdutoInput, Produto produto) {
        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        return FotoProduto.builder()
                .produto(produto)
                .nomeArquivo(fotoStorage.gerarNomeArquivo(arquivo.getOriginalFilename()))
                .descricao(fotoProdutoInput.getDescricao())
                .contentType(arquivo.getContentType())
                .tamanho(arquivo.getSize())
                .build();
    }
}
