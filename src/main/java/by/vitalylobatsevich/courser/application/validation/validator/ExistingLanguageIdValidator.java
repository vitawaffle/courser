package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.ExistingLanguageId;
import by.vitalylobatsevich.courser.database.repository.LanguageRepository;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ExistingLanguageIdValidator implements ConstraintValidator<ExistingLanguageId, Long> {

    private final LanguageRepository languageRepository;

    @Override
    public boolean isValid(final Long value, final ConstraintValidatorContext context) {
        return languageRepository.existsById(value);
    }

}
