package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.PropertiesPasswordValidationConfigurator;

import lombok.val;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertiesPasswordValidationConfiguratorTests {

    @Test
    void getActiveRules_ThereIsRulesInPropertyFile_ShouldReturnNotEmpty() {
        val configurator = new PropertiesPasswordValidationConfigurator(
                "classpath:validation-test1.properties");
        assertFalse(configurator.getActiveRules().isEmpty());
    }

    @Test
    void getActiveRules_InvalidMinimalLengthPropertyValue_ShouldDoesNotThrows() {
        val configurator = new PropertiesPasswordValidationConfigurator(
                "classpath:validation-test2.properties");
        assertDoesNotThrow(configurator::getActiveRules);
    }

    @Test
    void getActiveRules_InvalidBooleanPropertyValue_ShouldDoesNotThrows() {
        val configurator = new PropertiesPasswordValidationConfigurator(
                "classpath:validation-test3.properties");
        assertDoesNotThrow(configurator::getActiveRules);
    }

    @Test
    void getActiveRules_NoRulesInPropertyFile_ShouldReturnEmpty() {
        val configurator = new PropertiesPasswordValidationConfigurator(
                "classpath:validation-test4.properties");
        assertTrue(configurator.getActiveRules().isEmpty());
    }

    @Test
    void constructor_NotExistingPropertyFile_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> new PropertiesPasswordValidationConfigurator(
                "not-existing-file.properties"));
    }

}
