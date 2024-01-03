package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.EstadoNotFoundException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.model.Estado;
import io.github.alancs7.speedfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstadoService {

    public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNotFoundException(id));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            estadoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNotFoundException(id);

        } catch (DataIntegrityViolationException e) {
            throw new ResourceInUseException(
                    String.format(MSG_ESTADO_EM_USO, id));
        }
    }
}
