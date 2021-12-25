package by.vitalylobatsevich.courser.application.validation.password;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class PropertiesPasswordValidationConfigurator extends PasswordValidationConfigurator {

    private final Properties validationProperties = new Properties();

    public PropertiesPasswordValidationConfigurator(final String pathToPropertyFile) {
        try {
            validationProperties.load(
                    new FileInputStream(ResourceUtils.getFile(pathToPropertyFile)));
        } catch (final FileNotFoundException exception) {
            log.error("File validation.properties is not found", exception);
        } catch (final IOException exception) {
            log.error("Error of reading validation.properties file", exception);
        }
    }

    @Override
    protected Optional<Integer> getMinimalLength() {
        val minimalLengthProperty = validationProperties.getProperty("password.minimal-length");
        if (minimalLengthProperty != null) {
            try {
                return Optional.of(Integer.parseInt(minimalLengthProperty));
            } catch (final NumberFormatException exception) {
                log.error("Property password.minimal-length should be a valid number");
            }
        }
        return Optional.empty();
    }

    private Optional<Boolean> getBooleanRuleActivation(final String propertyKey) {
        val isRuleActive = validationProperties.getProperty(propertyKey);
        if (isRuleActive == null) {
            return Optional.empty();
        }
        if (!isRuleActive.equals("true") && !isRuleActive.equals("false")) {
            log.error("Property " + propertyKey + " should be true or false");
            return Optional.empty();
        }
        return Optional.of(isRuleActive.equals("true"));
    }

    @Override
    protected Optional<Boolean> isHasBigLetterActive() {
        return getBooleanRuleActivation("password.has-big-letter");
    }

    @Override
    protected Optional<Boolean> isHasSmallLetterActive() {
        return getBooleanRuleActivation("password.has-small-letter");
    }

    @Override
    protected Optional<Boolean> isHasNumberActive() {
        return getBooleanRuleActivation("password.has-number");
    }

}
