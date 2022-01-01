package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfirmationTokenRepository extends CourserRepository<EmailConfirmationToken, Long> {

    Option<EmailConfirmationToken> findByToken(String token);

    Option<EmailConfirmationToken> findByUser(User user);

}
