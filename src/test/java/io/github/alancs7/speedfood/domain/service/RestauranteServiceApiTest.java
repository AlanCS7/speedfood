package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import io.github.alancs7.speedfood.domain.repository.CozinhaRepository;
import io.github.alancs7.speedfood.domain.repository.RestauranteRepository;
import io.github.alancs7.speedfood.util.DatabaseCleaner;
import io.github.alancs7.speedfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RestauranteServiceApiTest {

    private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";

    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private String jsonRestauranteCorreto;
    private String jsonRestauranteSemFrete;
    private String jsonRestauranteSemCozinha;
    private String jsonRestauranteComCozinhaInexistente;

    private Restaurante burgerTopRestaurante;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
                "/json/correct/restaurante-new-york-barbecue.json");

        jsonRestauranteSemFrete = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurante-new-york-barbecue-sem-frete.json");

        jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurante-new-york-barbecue-sem-cozinha.json");

        jsonRestauranteComCozinhaInexistente = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurante-new-york-barbecue-com-cozinha-inexistente.json");

        databaseCleaner.clearTables();
        prepareDatas();
    }

    @DisplayName("Deve retornar status 200 ao consultar restaurantes")
    @Test
    void shouldReturnStatus200_WhenFetchingRestaurantes() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("Deve retornar status 201 ao criar um novo restaurante")
    @Test
    void shouldReturnStatus201_WhenCreateRestaurante() {
        given()
                .body(jsonRestauranteCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("Deve retornar status 400 ao criar um novo restaurante sem taxa frete")
    @Test
    void shouldReturnStatus400_WhenCreateRestauranteWithoutTaxaFrete() {
        given()
                .body(jsonRestauranteSemFrete)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("Deve retornar status 400 ao criar um novo restaurante sem cozinha")
    @Test
    void shouldReturnStatus400_WhenCreateRestauranteWithoutCozinha() {
        given()
                .body(jsonRestauranteSemCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("Deve retornar status 400 ao criar um novo restaurante com cozinha inexistente")
    @Test
    void shouldReturnStatus400_WhenCreateRestauranteWithCozinhaNonExistent() {
        given()
                .body(jsonRestauranteComCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE))
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("Deve retornar status 200 ao consultar um restaurante existente")
    @Test
    void shouldReturnStatus200_WhenFetchingExistentRestaurante() {
        given()
                .pathParam("id", burgerTopRestaurante.getId())
                .accept(ContentType.JSON)
        .when()
                .get("/{id}")
        .then()
                .body("nome", equalTo(burgerTopRestaurante.getNome()))
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("Deve retornar status 404 ao consultar um restaurante inexistente")
    @Test
    void shouldReturnStatus404_WhenFetchingNonExistentRestaurante() {
        given()
                .pathParam("id", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
        .when()
                .get("/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareDatas() {
        Cozinha cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhaRepository.save(cozinhaBrasileira);

        Cozinha cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");
        cozinhaRepository.save(cozinhaAmericana);

        burgerTopRestaurante = new Restaurante();
        burgerTopRestaurante.setNome("Burger Top");
        burgerTopRestaurante.setTaxaFrete(new BigDecimal(10));
        burgerTopRestaurante.setCozinha(cozinhaAmericana);
        restauranteRepository.save(burgerTopRestaurante);

        Restaurante comidaMineiraRestaurante = new Restaurante();
        comidaMineiraRestaurante.setNome("Comida Mineira");
        comidaMineiraRestaurante.setTaxaFrete(new BigDecimal(10));
        comidaMineiraRestaurante.setCozinha(cozinhaBrasileira);
        restauranteRepository.save(comidaMineiraRestaurante);
    }

}