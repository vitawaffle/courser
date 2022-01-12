package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.ExistingFileId;
import by.vitalylobatsevich.courser.database.repository.FileRepository;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ExistingFileIdValidator implements ConstraintValidator<ExistingFileId, Long> {

    private final FileRepository fileRepository;

    @Override
    public boolean isValid(final Long value, final ConstraintValidatorContext context) {
        return fileRepository.existsById(value);
    }

}
