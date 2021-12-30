package by.vitalylobatsevich.courser.application.service;

import io.vavr.collection.Map;

public interface ConfigurationService extends Service {

    Map<String, String> getActivePasswordRules();

}
