package by.vitalylobatsevich.courser.http.controller;

import by.vitalylobatsevich.courser.application.service.ConfigurationService;

import io.vavr.collection.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configuration")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @GetMapping("/password-rules")
    public Map<String, String> getPasswordRules() {
        return configurationService.getActivePasswordRules();
    }

}
