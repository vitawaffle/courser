package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HasBigLetterPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HasBigLetterPasswordRuleTests {

    final HasBigLetterPasswordRule rule = new HasBigLetterPasswordRule();

    @Test
    void isValid_HasBigLetter_ShouldReturnTrue() {
        assertTrue(rule.isValid("Testvalue"));
    }

}
