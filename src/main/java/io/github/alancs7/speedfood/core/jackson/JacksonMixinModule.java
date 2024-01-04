package io.github.alancs7.speedfood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.alancs7.speedfood.api.model.mixin.CidadeMixin;
import io.github.alancs7.speedfood.api.model.mixin.CozinhaMixin;
import io.github.alancs7.speedfood.api.model.mixin.RestauranteMixin;
import io.github.alancs7.speedfood.domain.model.Cidade;
import io.github.alancs7.speedfood.domain.model.Cozinha;
import io.github.alancs7.speedfood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }

}
