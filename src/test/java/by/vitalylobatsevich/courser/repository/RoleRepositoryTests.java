package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.repository.RoleRepository;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.factory.RoleFactory;
import by.vitalylobatsevich.courser.factory.implementation.RoleFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    RoleFactory roleFactory = new RoleFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(roleRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(roleRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(roleRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleRepository.save(roleFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> roleRepository.save(roleFactory.createValidEntity().updateName(null)));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleRepository.delete(new Role(1L)));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleRepository.delete(new Role(0L)));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> roleRepository.deleteById(0L));
    }

    @Test
    void existsByName_ExistingName_ShouldReturnTrue() {
        assertTrue(roleRepository.existsByName("TEST_ROLE_1"));
    }

    @Test
    void existsByName_NotExistingName_ShouldReturnFalse() {
        assertFalse(roleRepository.existsByName(""));
    }

}
