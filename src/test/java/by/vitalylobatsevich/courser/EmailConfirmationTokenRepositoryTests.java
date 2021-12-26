package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.EmailConfirmationTokenRepository;
import by.vitalylobatsevich.courser.factory.EmailConfirmationTokenFactory;
import by.vitalylobatsevich.courser.factory.implementation.EmailConfirmationTokenFactoryImpl;

import lombok.val;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmailConfirmationTokenRepositoryTests {

    @Autowired
    EmailConfirmationTokenRepository testedRepository;

    EmailConfirmationTokenFactory entityFactory = new EmailConfirmationTokenFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(testedRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(testedRepository.findById(1L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedRepository.save(entityFactory.createValidEntity()));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedRepository.delete(entityFactory.createEntityWithExistingId()));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedRepository.deleteById(1L));
    }

    @Test
    void findByToken_ExistingToken_ShouldReturnNotEmpty() {
        assertFalse(testedRepository.findByToken("TestToken1").isEmpty());
    }

    @Test
    void findByUser_UserWithExistingId_ShouldReturnNotEmpty() {
        val user = new User();
        user.setId(1L);
        assertFalse(testedRepository.findByUser(user).isEmpty());
    }

}
