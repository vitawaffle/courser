package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.validation.Email;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<Email, String> {

    private final org.apache.commons.validator.routines.EmailValidator emailValidator;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value == null || value.equals("") || emailValidator.isValid(value);
    }

}
