package by.vitalylobatsevich.courser.application.validation.password.rule;

public class HaveBigLetterPasswordRule implements PasswordRule {

    @Override
    public boolean isValid(final String value) {
        return value.matches(".*[A-ZА-Я].*");
    }

}
