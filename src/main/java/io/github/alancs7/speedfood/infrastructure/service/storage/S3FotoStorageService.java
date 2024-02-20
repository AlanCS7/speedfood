package io.github.alancs7.speedfood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.github.alancs7.speedfood.core.storage.StorageProperties;
import io.github.alancs7.speedfood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(novaFoto.getContentType());
            objectMetadata.setContentLength(novaFoto.getInputStream().available());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    arquivoPath,
                    novaFoto.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar o arquivo para Amazon S3.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String arquivoPath = getArquivoPath(nomeArquivo);

            amazonS3.deleteObject(storageProperties.getS3().getBucket(), arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo na Amazon S3.", e);
        }
    }

    private String getArquivoPath(String nomeArquivo) {
        return "%s/%s".formatted(storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }
}
