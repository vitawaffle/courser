package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.validation.password.rule.HaveNumberPasswordRule;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HaveNumberPasswordRuleTests {

    final HaveNumberPasswordRule rule = new HaveNumberPasswordRule();

    @Test
    void isValid_HasNumber_ShouldReturnTrue() {
        assertTrue(rule.isValid("testvalue1"));
    }

}
