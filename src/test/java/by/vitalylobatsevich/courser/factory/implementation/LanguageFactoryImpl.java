package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.factory.LanguageFactory;

public class LanguageFactoryImpl implements LanguageFactory {

    @Override
    public Language createValidEntity() {
        return Language.languageBuilder()
                .code("test")
                .build();
    }

}
