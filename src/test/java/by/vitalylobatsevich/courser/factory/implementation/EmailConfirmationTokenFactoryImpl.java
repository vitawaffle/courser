package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.EmailConfirmationTokenFactory;

import java.time.Instant;

public class EmailConfirmationTokenFactoryImpl implements EmailConfirmationTokenFactory {

    @Override
    public EmailConfirmationToken createValidEntity() {
        return new EmailConfirmationToken(
                null,
                "cc78ee24-5082-49b4-bb3f-9ccdeba312ea",
                new User(2L),
                Instant.now().plusMillis(86400000),
                Instant.now().plusMillis(60000)
        );
    }

}
