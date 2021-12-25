package by.vitalylobatsevich.courser.application.validation.password;

import by.vitalylobatsevich.courser.application.validation.password.rule.*;

import io.vavr.control.Option;

import lombok.val;

import java.util.ArrayList;
import java.util.Collection;

public abstract class PasswordValidationConfigurator {

    protected abstract Option<Integer> getMinimalLength();

    protected abstract Option<Boolean> isHasBigLetterActive();

    protected abstract Option<Boolean> isHasSmallLetterActive();

    protected abstract Option<Boolean> isHasNumberActive();

    public Collection<PasswordRule> getActiveRules() {
        val activeRules = new ArrayList<PasswordRule>();
        getMinimalLength().peek(minimalLength -> activeRules.add(new LengthPasswordRule(minimalLength)));
        isHasBigLetterActive().peek(isHaveBigLetterActive -> {
            if (isHaveBigLetterActive) {
                activeRules.add(new HasBigLetterPasswordRule());
            }
        });
        isHasSmallLetterActive().peek(isHaveSmallLetterActive -> {
            if (isHaveSmallLetterActive) {
                activeRules.add(new HasSmallLetterPasswordRule());
            }
        });
        isHasNumberActive().peek(isHaveNumberActive -> {
            if (isHaveNumberActive) {
                activeRules.add(new HasNumberPasswordRule());
            }
        });
        return activeRules;
    }

}
