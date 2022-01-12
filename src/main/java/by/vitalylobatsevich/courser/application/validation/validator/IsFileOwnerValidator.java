package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.IsFileOwner;
import by.vitalylobatsevich.courser.database.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class IsFileOwnerValidator implements ConstraintValidator<IsFileOwner, Long> {

    private final FileRepository fileRepository;

    @Override
    public boolean isValid(final Long value, final ConstraintValidatorContext context) {
        return fileRepository.findById(value)
                .map(
                        file -> file.getUser()
                                .getEmail()
                                .equals(SecurityContextHolder.getContext().getAuthentication().getName())
                )
                .getOrElse(true);
    }

}
