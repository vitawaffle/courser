package by.vitalylobatsevich.courser.application.validation.password;

import by.vitalylobatsevich.courser.application.validation.password.rule.*;

import lombok.val;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class PasswordValidationConfigurator {

    protected abstract Optional<Integer> getMinimalLength();

    protected abstract Optional<Boolean> isHasBigLetterActive();

    protected abstract Optional<Boolean> isHasSmallLetterActive();

    protected abstract Optional<Boolean> isHasNumberActive();

    public Collection<PasswordRule> getActiveRules() {
        val activeRules = new ArrayList<PasswordRule>();
        getMinimalLength().ifPresent(minimalLength -> activeRules.add(new LengthPasswordRule(minimalLength)));
        isHasBigLetterActive().ifPresent(isHaveBigLetterActive -> {
            if (isHaveBigLetterActive) {
                activeRules.add(new HasBigLetterPasswordRule());
            }
        });
        isHasSmallLetterActive().ifPresent(isHaveSmallLetterActive -> {
            if (isHaveSmallLetterActive) {
                activeRules.add(new HasSmallLetterPasswordRule());
            }
        });
        isHasNumberActive().ifPresent(isHaveNumberActive -> {
            if (isHaveNumberActive) {
                activeRules.add(new HasNumberPasswordRule());
            }
        });
        return activeRules;
    }

}
