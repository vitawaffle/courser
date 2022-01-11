package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.application.service.implementation.exception.StorageException;
import by.vitalylobatsevich.courser.database.repository.FileRepository;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {

    private final FileRepository fileRepository;

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(final FileRepository fileRepository, final Path rootLocation) {
        this.fileRepository = fileRepository;
        this.rootLocation = rootLocation;
    }

    @Override
    public void store(final MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            val destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("Can not store file outside current directory");
            }
            try (val inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (final IOException exception) {
            throw new StorageException("Failed to store file", exception);
        }
    }

    @Override
    public Seq<Path> loadAll() {
        return null;
    }

    @Override
    public Option<Path> loadByPath(final String path) {
        return null;
    }

    @Override
    public Option<Resource> loadByPathAsResource(final String path) {
        return null;
    }

    @Override
    public void deleteByPath(final String path) {
    }

}
