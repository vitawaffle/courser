package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;

import org.springframework.web.multipart.MultipartFile;

public interface FileService extends AppService {

    File store(MultipartFile file, User user);

    File storeForCurrentUser(MultipartFile file);

}
