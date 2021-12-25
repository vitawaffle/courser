package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.database.repository.RoleRepository;
import by.vitalylobatsevich.courser.factory.RoleFactory;
import by.vitalylobatsevich.courser.factory.implementation.RoleFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTests {

    @Autowired
    RoleRepository testedRepository;

    RoleFactory entityFactory = new RoleFactoryImpl();

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
    void existsByName_ExistingName_ShouldReturnTrue() {
        assertTrue(testedRepository.existsByName("TEST_ROLE_1"));
    }

}
