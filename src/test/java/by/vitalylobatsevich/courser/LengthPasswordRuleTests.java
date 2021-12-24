package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.LengthPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LengthPasswordRuleTests {

    final LengthPasswordRule rule = new LengthPasswordRule(8);

    @Test
    void isValid_MoreThan8Length_ShouldReturnTrue() {
        assertTrue(rule.isValid("12345678"));
    }

    @Test
    void isValid_LessThan8Length_ShouldReturnFalse() {
        assertFalse(rule.isValid("1234567"));
    }

}
