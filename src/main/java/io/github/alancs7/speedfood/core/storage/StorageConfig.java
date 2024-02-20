package io.github.alancs7.speedfood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.github.alancs7.speedfood.core.storage.StorageProperties.TipoStorage;
import io.github.alancs7.speedfood.domain.service.FotoStorageService;
import io.github.alancs7.speedfood.infrastructure.service.storage.LocalFotoStorageService;
import io.github.alancs7.speedfood.infrastructure.service.storage.S3FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getIdChaveAcesso(),
                storageProperties.getS3().getChaveAcessoSecreta()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        return TipoStorage.S3.equals(storageProperties.getTipo()) ?
                new S3FotoStorageService() : new LocalFotoStorageService();

    }
}
