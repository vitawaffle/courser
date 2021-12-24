package by.vitalylobatsevich.courser.application.validation.password.rule;

public class HaveNumberPasswordRule implements PasswordRule {

    @Override
    public boolean isValid(final String value) {
        return value.matches(".*[0-9].*");
    }

}
