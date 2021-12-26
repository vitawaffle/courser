package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

@org.springframework.stereotype.Repository
public interface EmailConfirmationTokenRepository extends Repository<EmailConfirmationToken, Long> {

    Option<EmailConfirmationToken> findByToken(String token);

    Option<EmailConfirmationToken> findByUser(User user);

}
