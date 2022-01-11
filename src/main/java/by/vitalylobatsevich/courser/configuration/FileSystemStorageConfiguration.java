package by.vitalylobatsevich.courser.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileSystemStorageConfiguration {

    @Value("${courser.storage.root-location}")
    private String rootLocationName;

    @Bean
    public Path rootLocation() {
        return Paths.get(rootLocationName);
    }

}
