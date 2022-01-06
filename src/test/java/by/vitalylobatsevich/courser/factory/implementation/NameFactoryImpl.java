package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.NameFactory;

public class NameFactoryImpl implements NameFactory {

    @Override
    public Name createValidEntity() {
        return Name.nameBuilder()
                .id(new NameId())
                .firstName("Test First Name")
                .lastName("Test Last Name")
                .patronymic("Test Patronymic")
                .language(Language.languageBuilder().id(1L).build())
                .user(User.userBuilder().id(2L).build())
                .build();
    }

}
