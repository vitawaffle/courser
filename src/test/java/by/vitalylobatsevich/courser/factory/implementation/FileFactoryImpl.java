package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.FileFactory;

public class FileFactoryImpl implements FileFactory {

    @Override
    public File createValidEntity() {
        return File.builder()
                .name("file.test")
                .user(User.builder().id(1L).build())
                .build();
    }

}
