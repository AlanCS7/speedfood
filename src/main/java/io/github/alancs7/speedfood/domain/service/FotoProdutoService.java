package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.api.model.input.FotoProdutoInput;
import io.github.alancs7.speedfood.domain.exception.FotoProdutoNotFoundException;
import io.github.alancs7.speedfood.domain.model.FotoProduto;
import io.github.alancs7.speedfood.domain.model.Produto;
import io.github.alancs7.speedfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static io.github.alancs7.speedfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(Produto produto, FotoProdutoInput fotoProdutoInput) throws IOException {
        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(produto.getId());

        String nomeArquivoAntigo = null;
        if (fotoExistente.isPresent()) {
            nomeArquivoAntigo = fotoExistente.get().getNomeArquivo();
            produtoRepository.deleteFoto(fotoExistente.get());
        }

        FotoProduto foto = criarFotoProduto(fotoProdutoInput);
        foto.setProduto(produto);

        foto = produtoRepository.saveFoto(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto
                .builder()
                .nomeArquivo(foto.getNomeArquivo())
                .contentType(foto.getContentType())
                .inputStream(fotoProdutoInput.getArquivo().getInputStream())
                .build();

        fotoStorage.substituir(nomeArquivoAntigo, novaFoto);

        return foto;
    }

    private FotoProduto criarFotoProduto(FotoProdutoInput fotoProdutoInput) {
        MultipartFile arquivo = fotoProdutoInput.getArquivo();
        String nomeArquivo = fotoStorage.gerarNomeArquivo(arquivo.getOriginalFilename());

        return FotoProduto.builder()
                .nomeArquivo(nomeArquivo)
                .descricao(fotoProdutoInput.getDescricao())
                .contentType(arquivo.getContentType())
                .tamanho(arquivo.getSize())
                .build();
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNotFoundException(produtoId, restauranteId));
    }

    @Transactional
    public void excluir(Long restauranteId, Long produtoId) {
        FotoProduto fotoProduto = this.buscarOuFalhar(restauranteId, produtoId);

        produtoRepository.deleteFoto(fotoProduto);
        produtoRepository.flush();

        fotoStorage.remover(fotoProduto.getNomeArquivo());
    }
}
