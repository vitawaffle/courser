package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.FileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final StorageService storageService;

    private final FileRepository fileRepository;

    private final AuthService authService;

    @Override
    public File store(final MultipartFile file, final User user) {
        return fileRepository.save(
                File.builder()
                        .name(storageService.store(file))
                        .user(user)
                        .build()
        );
    }

    @Override
    public File storeForCurrentUser(final MultipartFile file) {
        return store(file, authService.getUser());
    }

}
