package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.LanguageService;
import by.vitalylobatsevich.courser.database.entity.Language;

import io.vavr.collection.Seq;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public Seq<Language> getAll() {
        return languageService.getAll();
    }

}
