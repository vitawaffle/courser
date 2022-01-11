package by.vitalylobatsevich.courser.application.service;

import io.vavr.collection.Seq;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService extends AppService {

    String store(MultipartFile file);

    Seq<Path> loadAll();

    Path loadByFilename(String filename);

    Resource loadByFilenameAsResource(String filename);

    void deleteByFilename(String filename);

}
