package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HasSmallLetterPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HasSmallLetterPasswordRuleTests {

    final HasSmallLetterPasswordRule rule = new HasSmallLetterPasswordRule();

    @Test
    void isValid_HasSmallLetter_ShouldReturnTrue() {
        assertTrue(rule.isValid("tESTVALUE"));
    }

}
