package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.repository.FileRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final StorageService storageService;

    private final FileRepository fileRepository;

    private final UserRepository userRepository;

    @Override
    public File store(MultipartFile file, String username) {
        return fileRepository.save(
                File.builder()
                        .name(storageService.store(file))
                        .user(
                                userRepository.findByEmail(username)
                                        .getOrElseThrow(() -> new UsernameNotFoundException(username))
                        )
                        .build()
        );
    }

}
