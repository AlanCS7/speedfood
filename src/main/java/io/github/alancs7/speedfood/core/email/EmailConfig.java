package io.github.alancs7.speedfood.core.email;

import io.github.alancs7.speedfood.domain.service.EnvioEmailService;
import io.github.alancs7.speedfood.infrastructure.service.email.FakeEnvioEmailService;
import io.github.alancs7.speedfood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        return switch (emailProperties.getImpl()) {
            case FAKE -> new FakeEnvioEmailService();
            case SMTP -> new SmtpEnvioEmailService();
            default -> null;
        };
    }
}
