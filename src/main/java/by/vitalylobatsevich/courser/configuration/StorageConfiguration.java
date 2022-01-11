package by.vitalylobatsevich.courser.configuration;

import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.application.service.implementation.FileSystemStorageService;

import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class StorageConfiguration {

    @Bean
    @Autowired
    public StorageService storageService(final Path rootLocation) {
        val fileSystemStorageService = new FileSystemStorageService(rootLocation);
        fileSystemStorageService.init();
        return fileSystemStorageService;
    }

}
