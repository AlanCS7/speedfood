package io.github.alancs7.speedfood.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import io.github.alancs7.speedfood.api.exception.ApiError;
import io.github.alancs7.speedfood.api.model.dto.CozinhaDto;
import io.github.alancs7.speedfood.api.openapi.model.CozinhaDtoOpenApi;
import io.github.alancs7.speedfood.api.openapi.model.PageableOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.alancs7.speedfood.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponses())
                .globalResponses(HttpMethod.POST, globalPostPutResponses())
                .globalResponses(HttpMethod.PUT, globalPostPutResponses())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponses())
                .additionalModels(typeResolver.resolve(ApiError.class))
                .directModelSubstitute(Pageable.class, PageableOpenApi.class)
                .ignoredParameterTypes(ServletWebRequest.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, CozinhaDto.class),
                        CozinhaDtoOpenApi.class
                ))
                .apiInfo(apiInfo())
                .tags(
                        new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Grupos", "Gerencia os grupos de usuários"),
                        new Tag("Cozinhas", "Gerencia as cozinhas"),
                        new Tag("FormasPagamento", "Gerencia as formas de pagamento")
                );
    }

    private List<Response> globalGetResponses() {
        return List.of(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que seja aceita pelo consumidor")
                        .build()
        );
    }

    private List<Response> globalPostPutResponses() {
        return List.of(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que seja aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build()
        );
    }

    private List<Response> globalDeleteResponses() {
        return List.of(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getApiErrorModel())
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpeedFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1.0")
                .contact(new Contact("SpeedFood", "https://github.com/AlanCS7", "alancss.contact@gmail.com"))
                .build();
    }

    private Consumer<RepresentationBuilder> getApiErrorModel() {
        return representation -> representation.model(model ->
                model.referenceModel(referenceModel ->
                        referenceModel.key(key ->
                                key.qualifiedModelName(qualifiedModelName ->
                                        qualifiedModelName
                                                .namespace("io.github.alancs7.speedfood.api.exception")
                                                .name("ApiError")))));
    }
}
