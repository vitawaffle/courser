package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.EmailConfirmationTokenFactory;

import java.time.Instant;

public class EmailConfirmationTokenFactoryImpl implements EmailConfirmationTokenFactory {

    @Override
    public EmailConfirmationToken createValidEntity() {
        return EmailConfirmationToken.builder()
                .token("cc78ee24-5082-49b4-bb3f-9ccdeba312ea")
                .user(User.builder().id(2L).build())
                .expirationDate(Instant.now().plusMillis(86400000))
                .canBeResend(Instant.now().plusMillis(60000))
                .build();
    }

}
