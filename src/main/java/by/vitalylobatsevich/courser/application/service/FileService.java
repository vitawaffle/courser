package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService extends AppService {

    File store(MultipartFile file, String username);

}
