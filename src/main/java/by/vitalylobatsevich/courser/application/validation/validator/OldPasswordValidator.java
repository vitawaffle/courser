package by.vitalylobatsevich.courser.application.validation.validator;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.validation.OldPassword;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OldPasswordValidator implements ConstraintValidator<OldPassword, String> {

    private final PasswordEncoder passwordEncoder;

    private final AuthService authService;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value == null || passwordEncoder.matches(value, authService.getUser().getPassword());
    }

}
