package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.exception.BusinessException;
import io.github.alancs7.speedfood.domain.exception.RestauranteNotFoundException;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.model.Restaurante;
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
class RestauranteServiceIT {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CozinhaService cozinhaService;

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
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("2"));

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
        Restaurante restaurante = new Restaurante();
        restaurante.setTaxaFrete(new BigDecimal("2"));

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        // acao e validacao
        assertThrows(ConstraintViolationException.class, () -> {
            restauranteService.salvar(restaurante);
        });
    }

    @DisplayName("Deve falhar ao criar restaurante sem taxa frete")
    @Test
    void shouldFailToCreateRestauranteWithoutTaxaFrete() {
        // cenario
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        restaurante.setCozinha(cozinha);

        // acao e validacao
        assertThrows(ConstraintViolationException.class, () -> {
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
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("2"));

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

        cozinhaService.salvar(cozinha);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Comida mineira");
        restaurante.setTaxaFrete(new BigDecimal("5.00"));
        restaurante.setCozinha(cozinha);

        restauranteService.salvar(restaurante);
    }
}