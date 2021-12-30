package by.vitalylobatsevich.courser.application.validation.password.rule;

import io.vavr.Tuple2;

public class HasSmallLetterPasswordRule implements PasswordRule {

    @Override
    public boolean isValid(final String value) {
        return value.matches(".*[a-zа-я].*");
    }

    @Override
    public Tuple2<String, String> toTuple() {
        return new Tuple2<>(this.getClass().getSimpleName(), "true");
    }

}
