package by.vitalylobatsevich.courser.application.validation.password.rule;

public class HaveSmallLetterPasswordRule implements PasswordRule {

    @Override
    public boolean isValid(final String value) {
        return value.matches(".*[a-zа-я].*");
    }

}
