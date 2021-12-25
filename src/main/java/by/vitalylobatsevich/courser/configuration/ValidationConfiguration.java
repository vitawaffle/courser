package by.vitalylobatsevich.courser.configuration;

import by.vitalylobatsevich.courser.application.validation.password.PasswordValidationConfigurator;
import by.vitalylobatsevich.courser.application.validation.password.PropertiesPasswordValidationConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfiguration {

    @Bean
    public PasswordValidationConfigurator passwordValidationConfigurator() {
        return new PropertiesPasswordValidationConfigurator("classpath:validation.properties");
    }

}
