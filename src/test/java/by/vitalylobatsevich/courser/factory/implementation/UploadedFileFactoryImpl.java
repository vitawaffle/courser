package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.UploadedFile;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.UploadedFileFactory;

public class UploadedFileFactoryImpl implements UploadedFileFactory {

    @Override
    public UploadedFile createValidEntity() {
        return new UploadedFile(
                null,
                "uploaded/test-path",
                new User(1L)
        );
    }

}
