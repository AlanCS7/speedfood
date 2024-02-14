package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.RestauranteNotFoundException;
import io.github.alancs7.speedfood.domain.model.*;
import io.github.alancs7.speedfood.util.DatabaseCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class RestauranteServiceIT {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        databaseCleaner.clearTables();
        prepareDatas();
    }

    @DisplayName("Deve criar restaurante com sucesso")
    @Test
    void shouldCreateRestauranteWithSuccess() {
        // cenario
        Estado estado = new Estado();
        estado.setNome("São Paulo");

        estado = estadoService.salvar(estado);

        Cidade cidade = new Cidade();
        cidade.setNome("Guarulhos");
        cidade.setEstado(estado);

        cidade = cidadeService.salvar(cidade);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("2"));
        restaurante.setEndereco(endereco);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        // acao
        restaurante = restauranteService.salvar(restaurante);

        // validacao
        assertNotNull(restaurante);
        assertNotNull(restaurante.getId());
    }

    @DisplayName("Deve falhar ao criar restaurante sem nome")
    @Test
    void shouldFailToCreateRestauranteWithoutNome() {
        // cenario
        Estado estado = new Estado();
        estado.setId(1L);

        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setEstado(estado);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);

        Restaurante restaurante = new Restaurante();
        restaurante.setTaxaFrete(new BigDecimal("2"));
        restaurante.setEndereco(endereco);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        // acao e validacao
        assertThrows(DataIntegrityViolationException.class, () -> {
            restauranteService.salvar(restaurante);
        });
    }

    @DisplayName("Deve falhar ao criar restaurante sem taxa frete")
    @Test
    void shouldFailToCreateRestauranteWithoutTaxaFrete() {
        // cenario
        Estado estado = new Estado();
        estado.setId(1L);

        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setEstado(estado);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setEndereco(endereco);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        // acao e validacao
        assertThrows(DataIntegrityViolationException.class, () -> {
            restauranteService.salvar(restaurante);
        });
    }

    @DisplayName("Deve falhar ao criar restaurante sem cozinha")
    @Test
    void shouldFailToCreateRestauranteWithoutCozinha() {
        // cenario
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("2"));

        // acao e validacao
        assertThrows(NullPointerException.class, () -> {
            restauranteService.salvar(restaurante);
        });
    }

    @DisplayName("Deve falhar ao criar restaurante com cozinha inexistente")
    @Test
    void shouldFailToCreateRestauranteWithCozinhaNonExistent() {
        // cenario
        Estado estado = new Estado();
        estado.setNome("São Paulo");

        estado = estadoService.salvar(estado);

        Cidade cidade = new Cidade();
        cidade.setNome("Guarulhos");
        cidade.setEstado(estado);

        cidade = cidadeService.salvar(cidade);

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("2"));
        restaurante.setEndereco(endereco);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(10L);
        restaurante.setCozinha(cozinha);

        // acao e validacao
        assertThrows(BusinessException.class, () -> {
            restauranteService.salvar(restaurante);
        });
    }

    @DisplayName("Deve falhar ao remover restaurante inexistente")
    @Test
    void shouldFailToRemoveNonExistentRestaurante() {
        // acao e validacao
        assertThrows(RestauranteNotFoundException.class, () -> {
            restauranteService.excluir(150L);
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