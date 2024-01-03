package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.CozinhaNotFoundException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNotFoundException(id));
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new ResourceInUseException(
                    String.format(MSG_COZINHA_EM_USO, id));
        }
    }
}
