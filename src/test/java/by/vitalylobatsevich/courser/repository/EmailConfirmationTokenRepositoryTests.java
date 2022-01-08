package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.EmailConfirmationToken;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.EmailConfirmationTokenRepository;
import by.vitalylobatsevich.courser.factory.EmailConfirmationTokenFactory;
import by.vitalylobatsevich.courser.factory.implementation.EmailConfirmationTokenFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailConfirmationTokenRepositoryTests {

    @Autowired
    EmailConfirmationTokenRepository emailConfirmationTokenRepository;

    EmailConfirmationTokenFactory emailConfirmationTokenFactory = new EmailConfirmationTokenFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(emailConfirmationTokenRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(emailConfirmationTokenRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(emailConfirmationTokenRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(
                () -> emailConfirmationTokenRepository.save(emailConfirmationTokenFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class,() -> emailConfirmationTokenRepository.save(
                emailConfirmationTokenFactory.createValidEntity().updater().token(null).update()));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(
                () -> emailConfirmationTokenRepository.delete(EmailConfirmationToken.builder().id(1L).build()));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(
                () -> emailConfirmationTokenRepository.delete(EmailConfirmationToken.builder().id(0L).build()));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> emailConfirmationTokenRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> emailConfirmationTokenRepository.deleteById(0L));
    }

    @Test
    void findByToken_ExistingToken_ShouldReturnNotEmpty() {
        assertFalse(emailConfirmationTokenRepository.findByToken("0aa0f472-a757-407e-b5af-131ff8d6c8a9").isEmpty());
    }

    @Test
    void findByToken_NotExistingToken_ShouldReturnEmpty() {
        assertTrue(emailConfirmationTokenRepository.findByToken("").isEmpty());
    }

    @Test
    void findByUser_UserWithExistingId_ShouldReturnNotEmpty() {
        assertFalse(emailConfirmationTokenRepository.findByUser(User.builder().id(1L).build()).isEmpty());
    }

    @Test
    void findByUser_UserWithNotExistingId_ShouldReturnEmpty() {
        assertTrue(emailConfirmationTokenRepository.findByUser(User.builder().id(0L).build()).isEmpty());
    }

}
