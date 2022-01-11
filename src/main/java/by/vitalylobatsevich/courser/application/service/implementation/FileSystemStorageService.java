package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.application.service.implementation.exception.FileNotFoundStorageException;
import by.vitalylobatsevich.courser.application.service.implementation.exception.StorageException;

import io.vavr.collection.List;
import io.vavr.collection.Seq;

import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(final Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @Override
    public String store(final MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }

            val destinationFile = this.rootLocation.resolve(Paths.get(UUID.randomUUID().toString()))
                    .normalize()
                    .toAbsolutePath();

            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("Failed to store file outside current directory");
            }

            try (val inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return destinationFile.getFileName().toString();
        } catch (final IOException exception) {
            throw new StorageException("Failed to store file", exception);
        }
    }

    @Override
    public Seq<Path> loadAll() {
        try {
            return List.ofAll(
                    Files.walk(this.rootLocation, 1)
                            .filter(path -> !path.equals(this.rootLocation))
                            .map(this.rootLocation::relativize)
            );
        } catch (final IOException exception) {
            throw new StorageException("Failed to read stored files", exception);
        }
    }

    @Override
    public Path loadByFilename(final String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadByFilenameAsResource(final String filename) {
        try {
            val file = loadByFilename(filename);
            val resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new FileNotFoundStorageException("Failed to read file: " + filename);
        } catch (final MalformedURLException exception) {
            throw new FileNotFoundStorageException("Failed to read file: " + filename);
        }
    }

    @Override
    public void deleteByFilename(final String filename) {
        try {
            Files.delete(this.rootLocation.resolve(filename));
        } catch (final IOException exception) {
            throw new StorageException("Failed to delete stored file", exception);
        }
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (final IOException exception) {
            throw new StorageException("Failed to initialize storage", exception);
        }
    }

}
