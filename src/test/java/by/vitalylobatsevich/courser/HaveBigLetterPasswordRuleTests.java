package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HaveBigLetterPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HaveBigLetterPasswordRuleTests {

    final HaveBigLetterPasswordRule rule = new HaveBigLetterPasswordRule();

    @Test
    void isValid_HasBigLetter_ShouldReturnTrue() {
        assertTrue(rule.isValid("Testvalue"));
    }

}
