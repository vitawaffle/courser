package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.FileRepository;

import io.vavr.control.Option;
import io.vavr.collection.Seq;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final StorageService storageService;

    private final FileRepository fileRepository;

    private final AuthService authService;

    @Override
    public Option<File> getById(final Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public Seq<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File save(final File file) {
        return fileRepository.save(file);
    }

    @Override
    public void delete(final File file) {
        fileRepository.delete(file);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            fileRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignored) {
        }
    }

    @Override
    public File storeForUser(final MultipartFile file, final User user) {
        return fileRepository.save(
                File.builder()
                        .name(storageService.store(file))
                        .user(user)
                        .build()
        );
    }

    @Override
    public File storeForCurrentUser(final MultipartFile file) {
        return storeForUser(file, authService.getUser());
    }

    @Override
    public Resource load(final File file) {
        return storageService.loadByFilenameAsResource(file.getName());
    }

    @Override
    public void deleteFromStorage(final File file) {
        storageService.deleteByFilename(file.getName());
        fileRepository.delete(file);
    }

    @Override
    public void deleteByIdFromStorage(final Long id) {
        fileRepository.findById(id).peek(file -> deleteFromStorage(file));
    }

}
