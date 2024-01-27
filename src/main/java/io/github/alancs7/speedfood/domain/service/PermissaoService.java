package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.PermissaoNotFoundException;
import io.github.alancs7.speedfood.domain.model.Permissao;
import io.github.alancs7.speedfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNotFoundException(id));
    }
}
