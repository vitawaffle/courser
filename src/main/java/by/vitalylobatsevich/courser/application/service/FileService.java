package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends CollectionService<File, Long> {

    File storeForUser(MultipartFile file, User user);

    File storeForCurrentUser(MultipartFile file);

    Resource load(File file);

    void deleteFromStorage(File file);

    void deleteByIdFromStorage(Long id);

}
