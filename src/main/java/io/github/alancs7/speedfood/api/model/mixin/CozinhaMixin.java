package io.github.alancs7.speedfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.alancs7.speedfood.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {

    @JsonIgnore
    private final List<Restaurante> restaurantes = new ArrayList<>();
}
