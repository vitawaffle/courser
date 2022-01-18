package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.Password;
import by.vitalylobatsevich.courser.application.validation.password.PasswordValidationConfigurator;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final PasswordValidationConfigurator passwordValidationConfigurator;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value == null || passwordValidationConfigurator.getActiveRules()
                .filter(rule -> !rule.isValid(value))
                .isEmpty();
    }

}
