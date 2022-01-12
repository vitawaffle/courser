package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.AvatarFactory;

public class AvatarFactoryImpl implements AvatarFactory {

    @Override
    public Avatar createValidEntity() {
        return Avatar.builder()
                .user(
                        User.builder()
                                .id(1L)
                                .build()
                )
                .file(
                        File.builder()
                                .id(2L)
                                .build()
                )
                .build();
    }

}
