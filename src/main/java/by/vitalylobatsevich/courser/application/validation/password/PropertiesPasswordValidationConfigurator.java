package by.vitalylobatsevich.courser.application.validation.password;

import io.vavr.control.Option;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesPasswordValidationConfigurator extends PasswordValidationConfigurator {

    private final Properties validationProperties = new Properties();

    public PropertiesPasswordValidationConfigurator(final String pathToPropertyFile) {
        try {
            validationProperties.load(new FileInputStream(
                    ResourceUtils.getFile(pathToPropertyFile)
            ));
        } catch (final FileNotFoundException exception) {
            log.error("File validation.properties is not found", exception);
        } catch (final IOException exception) {
            log.error("Error of reading validation.properties file", exception);
        }
    }

    @Override
    protected Option<Integer> getMinimalLength() {
        val minimalLengthProperty = validationProperties.getProperty("password.minimal-length");
        if (minimalLengthProperty != null) {
            try {
                return Option.of(Integer.parseInt(minimalLengthProperty));
            } catch (final NumberFormatException exception) {
                log.error("Property password.minimal-length should be a valid number");
            }
        }
        return Option.none();
    }

    private Option<Boolean> getBooleanRuleActivation(final String propertyKey) {
        val isRuleActive = validationProperties.getProperty(propertyKey);
        if (isRuleActive == null) {
            return Option.none();
        }
        if (!isRuleActive.equals("true") && !isRuleActive.equals("false")) {
            log.error("Property " + propertyKey + " should be true or false");
            return Option.none();
        }
        return Option.of(isRuleActive.equals("true"));
    }

    @Override
    protected Option<Boolean> isHasBigLetterActive() {
        return getBooleanRuleActivation("password.has-big-letter");
    }

    @Override
    protected Option<Boolean> isHasSmallLetterActive() {
        return getBooleanRuleActivation("password.has-small-letter");
    }

    @Override
    protected Option<Boolean> isHasNumberActive() {
        return getBooleanRuleActivation("password.has-number");
    }

}
