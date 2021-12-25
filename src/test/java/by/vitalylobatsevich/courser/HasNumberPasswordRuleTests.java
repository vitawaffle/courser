package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HasNumberPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HasNumberPasswordRuleTests {

    final HasNumberPasswordRule rule = new HasNumberPasswordRule();

    @Test
    void isValid_HasNumber_ShouldReturnTrue() {
        assertTrue(rule.isValid("testvalue1"));
    }

}
