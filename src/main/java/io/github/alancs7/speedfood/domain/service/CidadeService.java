package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.CidadeNotFoundException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.model.Estado;
import io.github.alancs7.speedfood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNotFoundException(id));
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoService.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long id) {
        try {
            cidadeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new ResourceInUseException(
                    String.format(MSG_CIDADE_EM_USO, id));
        }
    }
}
