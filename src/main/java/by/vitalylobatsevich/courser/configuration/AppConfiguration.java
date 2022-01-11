package by.vitalylobatsevich.courser.configuration;

import com.fasterxml.jackson.databind.Module;

import io.vavr.jackson.datatype.VavrModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AppConfiguration {

    @Bean
    public Module vavrModule() {
        return new VavrModule();
    }

}
