package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HaveSmallLetterPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HaveSmallLetterPasswordRuleTests {

    final HaveSmallLetterPasswordRule rule = new HaveSmallLetterPasswordRule();

    @Test
    void isValid_HasSmallLetter_ShouldReturnTrue() {
        assertTrue(rule.isValid("tESTVALUE"));
    }

}
