package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Language;

public interface LanguageService extends CollectionService<Language, Long> {

    boolean existsByCode(String code);

}
