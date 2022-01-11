package by.vitalylobatsevich.courser.application.service;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService extends AppService {

    void store(MultipartFile file);

    Seq<Path> loadAll();

    Option<Path> loadByPath(String path);

    Option<Resource> loadByPathAsResource(String path);

    void deleteByPath(String path);

}
