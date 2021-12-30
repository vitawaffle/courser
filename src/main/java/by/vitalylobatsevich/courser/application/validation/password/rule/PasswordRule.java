package by.vitalylobatsevich.courser.application.validation.password.rule;

import io.vavr.Tuple2;

public interface PasswordRule {

    boolean isValid(String value);

    Tuple2<String, String> toTuple();

}
