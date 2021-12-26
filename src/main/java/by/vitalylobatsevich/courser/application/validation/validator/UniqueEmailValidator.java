package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.UniqueEmail;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.equals("") || !userRepository.existsByEmail(value);
    }

}
