package by.vitalylobatsevich.courser.application.validation.password;

import by.vitalylobatsevich.courser.application.validation.password.rule.*;

import lombok.val;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class PasswordValidationConfigurator {

    protected abstract Optional<Integer> getMinimalLength();

    protected abstract Optional<Boolean> isHaveBigLetterActive();

    protected abstract Optional<Boolean> isHaveSmallLetterActive();

    protected abstract Optional<Boolean> isHaveNumberActive();

    public Collection<PasswordRule> getActiveRules() {
        val activeRules = new ArrayList<PasswordRule>();
        getMinimalLength().ifPresent(minimalLength -> activeRules.add(new LengthPasswordRule(minimalLength)));
        isHaveBigLetterActive().ifPresent(isHaveBigLetterActive -> {
            if (isHaveBigLetterActive) {
                activeRules.add(new HaveBigLetterPasswordRule());
            }
        });
        isHaveSmallLetterActive().ifPresent(isHaveSmallLetterActive -> {
            if (isHaveSmallLetterActive) {
                activeRules.add(new HaveSmallLetterPasswordRule());
            }
        });
        isHaveNumberActive().ifPresent(isHaveNumberActive -> {
            if (isHaveNumberActive) {
                activeRules.add(new HaveNumberPasswordRule());
            }
        });
        return activeRules;
    }

}
