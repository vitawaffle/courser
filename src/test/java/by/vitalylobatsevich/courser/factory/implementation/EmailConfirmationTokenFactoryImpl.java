package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.EmailConfirmationTokenFactory;

import lombok.val;

import java.time.Instant;

public class EmailConfirmationTokenFactoryImpl implements EmailConfirmationTokenFactory {

    @Override
    public EmailConfirmationToken createValidEntity() {
        val user = new User();
        user.setId(2L);

        val emailConfirmationToken = new EmailConfirmationToken();
        emailConfirmationToken.setToken("TestToken");
        emailConfirmationToken.setUser(user);
        emailConfirmationToken.setExpirationDate(Instant.now());
        emailConfirmationToken.setCanBeResend(Instant.now());
        return emailConfirmationToken;
    }

    @Override
    public EmailConfirmationToken createEntityWithExistingId() {
        val emailConfirmationToken = createValidEntity();
        emailConfirmationToken.setId(1L);
        return emailConfirmationToken;
    }

}
