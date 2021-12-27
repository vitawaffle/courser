package by.vitalylobatsevich.courser.application.validation.password;

import by.vitalylobatsevich.courser.application.validation.password.rule.*;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public abstract class PasswordValidationConfigurator {

    protected abstract Option<Integer> getMinimalLength();

    protected abstract Option<Boolean> isHasBigLetterActive();

    protected abstract Option<Boolean> isHasSmallLetterActive();

    protected abstract Option<Boolean> isHasNumberActive();

    public Set<PasswordRule> getActiveRules() {
        return HashSet.of(
                getMinimalLength().map(LengthPasswordRule::new),
                isHasBigLetterActive()
                        .filter(isHasBigLetterActive -> isHasBigLetterActive)
                        .map(isHasBigLetterActive -> new HasBigLetterPasswordRule()),
                isHasSmallLetterActive()
                        .filter(isHasSmallLetterActive -> isHasSmallLetterActive)
                        .map(isHasSmallLetterActive -> new HasSmallLetterPasswordRule()),
                isHasNumberActive()
                        .filter(isHasNumberActive -> isHasNumberActive)
                        .map(isHasNumberActive -> new HasNumberPasswordRule())
        ).filter(rule -> !rule.isEmpty()).map(Option::get);
    }

}
