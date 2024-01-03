package io.github.alancs7.speedfood.domain.service;

import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.repository.CozinhaRepository;
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

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CozinhaServiceApiTest {

    private static final int COZINHA_ID_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Cozinha cozinhaAmericana;
    private int quantidadeCozinhasCadastradas;
    private String jsonCorretoCozinhaChinesa;

    @BeforeEach
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
                "/json/correct/cozinha-chinesa.json");

        databaseCleaner.clearTables();
        prepareDatas();
    }

    @DisplayName("Deve retornar status 200 ao consultar cozinhas")
    @Test
    void shouldReturnStatus200_WhenFetchingCozinhas() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("Deve conter a quantidade correta de cozinhas ao consultar cozinhas")
    @Test
    void shouldReturnCorrectQty_WhenFetchingCozinhas() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(quantidadeCozinhasCadastradas));
    }

    @DisplayName("Deve retornar status 201 ao criar uma nova cozinha")
    @Test
    void shouldReturnStatus201_WhenCreateCozinha() {
        given()
                .body(jsonCorretoCozinhaChinesa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("Deve retornar resposta e status correto ao consultar uma cozinha existente")
    @Test
    void shouldReturnCorrectResponseAndStatus_WhenFetchingExistentCozinha() {
        given()
                .pathParam("id", cozinhaAmericana.getId())
                .accept(ContentType.JSON)
        .when()
                .get("/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaAmericana.getNome()));
    }

    @DisplayName("Deve retornar status 404 ao consultar uma cozinha inexistente")
    @Test
    void shouldReturnStatus404_WhenFetchingNonExistentCozinha() {
        given()
                .pathParam("id", COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
        .when()
                .get("/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareDatas() {
        Cozinha tailandesa = new Cozinha();
        tailandesa.setNome("Tailandesa");

        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Americana");

        cozinhaRepository.saveAll(Arrays.asList(tailandesa, cozinhaAmericana));

        quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
    }

}