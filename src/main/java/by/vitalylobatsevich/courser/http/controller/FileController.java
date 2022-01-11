package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.database.entity.File;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class FileController extends AppRestController {

    private final FileService fileService;

    @PostMapping
    public File store(@RequestParam("file") MultipartFile file) {
        return fileService.store(file, getUsername());
    }

}
