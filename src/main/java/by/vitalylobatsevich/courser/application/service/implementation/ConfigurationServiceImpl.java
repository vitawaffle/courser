package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.ConfigurationService;

import by.vitalylobatsevich.courser.application.validation.password.PasswordValidationConfigurator;
import by.vitalylobatsevich.courser.application.validation.password.rule.PasswordRule;
import io.vavr.collection.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private final PasswordValidationConfigurator passwordValidationConfigurator;

    public Map<String, String> getActivePasswordRules() {
        return passwordValidationConfigurator.getActiveRules().toMap(PasswordRule::toTuple);
    }

}
