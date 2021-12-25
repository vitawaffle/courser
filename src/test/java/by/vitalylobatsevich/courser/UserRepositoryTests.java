package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.factory.UserFactory;
import by.vitalylobatsevich.courser.factory.implementation.UserFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTests {

    @Autowired
    UserRepository testedRepository;

    UserFactory entityFactory = new UserFactoryImpl();

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
    void existsByEmail_ExistingEmail_ShouldReturnTrue() {
        assertTrue(testedRepository.existsByEmail("TestUser1"));
    }

}
