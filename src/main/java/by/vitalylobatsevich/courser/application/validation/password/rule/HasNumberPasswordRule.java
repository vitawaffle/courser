package by.vitalylobatsevich.courser.application.validation.password.rule;

import io.vavr.Tuple2;

public class HasNumberPasswordRule implements PasswordRule {

    @Override
    public boolean isValid(final String value) {
        return value.matches(".*[0-9].*");
    }

    @Override
    public Tuple2<String, String> toTuple() {
        return new Tuple2<>(this.getClass().getSimpleName(), "true");
    }

}
