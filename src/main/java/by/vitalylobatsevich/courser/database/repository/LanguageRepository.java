package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Language;

import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends AppRepository<Language, Long> {

    boolean existsByCode(String code);

}
