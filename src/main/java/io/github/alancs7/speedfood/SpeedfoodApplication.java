package io.github.alancs7.speedfood;

import io.github.alancs7.speedfood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class SpeedfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedfoodApplication.class, args);
    }

}
