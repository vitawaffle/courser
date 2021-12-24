package by.vitalylobatsevich.courser.application.validation.password.rule;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LengthPasswordRule implements PasswordRule {

    private final int minimalPasswordLength;

    @Override
    public boolean isValid(final String value) {
        return value.length() >= minimalPasswordLength;
    }

}
