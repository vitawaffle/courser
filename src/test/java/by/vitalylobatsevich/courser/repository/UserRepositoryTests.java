package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.UserFactory;
import by.vitalylobatsevich.courser.factory.implementation.UserFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    UserFactory userFactory = new UserFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(userRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.save(userFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> userRepository.save(userFactory.createValidEntity().updateEmail(null)));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.delete(new User(1L)));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.delete(new User(0L)));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> userRepository.deleteById(0L));
    }

    @Test
    void existsByEmail_ExistingEmail_ShouldReturnTrue() {
        assertTrue(userRepository.existsByEmail("test.email1@test.org"));
    }

    @Test
    void existsByEmail_NotExistingEmail_ShouldReturnFalse() {
        assertFalse(userRepository.existsByEmail(""));
    }

    @Test
    void findByEmail_ExistingEmail_ShouldReturnNotEmpty() {
        assertFalse(userRepository.findByEmail("test.email1@test.org").isEmpty());
    }

    @Test
    void findByEmail_NotExistingEmail_ShouldReturnEmpty() {
        assertTrue(userRepository.findByEmail("").isEmpty());
    }

}
