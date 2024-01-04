package io.github.alancs7.speedfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.alancs7.speedfood.domain.model.Estado;

public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
