package by.vitalylobatsevich.courser.application.validation.password.rule;

import io.vavr.Tuple2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LengthPasswordRule implements PasswordRule {

    private final int minimalPasswordLength;

    @Override
    public boolean isValid(final String value) {
        return value.length() >= minimalPasswordLength;
    }

    @Override
    public Tuple2<String, String> toTuple() {
        return new Tuple2<>(this.getClass().getSimpleName(), String.valueOf(minimalPasswordLength));
    }

}
