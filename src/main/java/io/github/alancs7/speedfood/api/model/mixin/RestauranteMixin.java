package io.github.alancs7.speedfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.model.Endereco;
import io.github.alancs7.speedfood.domain.model.FormaPagamento;
import io.github.alancs7.speedfood.domain.model.Produto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnore
    private final List<FormaPagamento> formasPagamento = new ArrayList<>();
    @JsonIgnore
    private final List<Produto> produtos = new ArrayList<>();
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;
    @JsonIgnore
    private Endereco endereco;
    //    @JsonIgnore
    private OffsetDateTime dataCadastro;
    //    @JsonIgnore
    private OffsetDateTime dataAtualizacao;
}
