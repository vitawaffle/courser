package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.Password;
import by.vitalylobatsevich.courser.application.validation.password.PasswordValidationConfigurator;

import lombok.RequiredArgsConstructor;
import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final PasswordValidationConfigurator passwordValidationConfigurator;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        for (val passwordRule : passwordValidationConfigurator.getActiveRules()) {
            if (!passwordRule.isValid(value)) {
                return false;
            }
        }
        return true;
    }

}
