package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.LanguageService;
import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.repository.LanguageRepository;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public Seq<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Option<Language> getById(final Long id) {
        return languageRepository.findById(id);
    }

    @Override
    public Language save(final Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            languageRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public boolean existsByCode(final String code) {
        return languageRepository.existsByCode(code);
    }

}
