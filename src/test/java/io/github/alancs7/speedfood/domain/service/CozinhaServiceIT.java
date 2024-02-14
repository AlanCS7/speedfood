package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.CozinhaNotFoundException;
import io.github.alancs7.speedfood.domain.exception.ResourceInUseException;
import io.github.alancs7.speedfood.domain.model.*;
import io.github.alancs7.speedfood.util.DatabaseCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CozinhaServiceIT {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;


    @BeforeEach
    void setUp() {
        databaseCleaner.clearTables();
        prepareDatas();
    }

    @DisplayName("Deve criar cozinha com sucesso")
    @Test
    void shouldCreateCozinhaWithSuccess() {
        // cenario
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Chinesa");

        // acao
        cozinha = cozinhaService.salvar(cozinha);

        // validacao
        assertNotNull(cozinha);
        assertNotNull(cozinha.getId());
    }

    @DisplayName("Deve falhar ao criar cozinha sem nome")
    @Test
    void shouldFailToCreateCozinhaWithoutNome() {
        // cenario
        Cozinha cozinha = new Cozinha();

        // acao e validacao
        assertThrows(ConstraintViolationException.class, () -> {
            cozinhaService.salvar(cozinha);
        });
    }

    @DisplayName("Deve falhar ao remover cozinha em uso")
    @Test
    void shouldFailToRemoveCozinhaInUse() {
        // acao e validacao
        assertThrows(ResourceInUseException.class, () -> {
            cozinhaService.excluir(1L);
        });
    }

    @DisplayName("Deve falhar ao remover cozinha inexistente")
    @Test
    void shouldFailToRemoveNonExistentCozinha() {
        // acao e validacao
        assertThrows(CozinhaNotFoundException.class, () -> {
            cozinhaService.excluir(150L);
        });
    }

    private void prepareDatas() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");

        Estado estado = new Estado();
        estado.setNome("São Paulo");

        Cidade cidade = new Cidade();
        cidade.setNome("Guarulhos");
        cidade.setEstado(estado);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("5.00"));
        restaurante.setCozinha(cozinha);
        restaurante.setEndereco(endereco);

        cozinhaService.salvar(cozinha);
        estadoService.salvar(estado);
        cidadeService.salvar(cidade);
        restauranteService.salvar(restaurante);
    }
}