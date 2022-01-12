package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.repository.AvatarRepository;
import by.vitalylobatsevich.courser.factory.AvatarFactory;
import by.vitalylobatsevich.courser.factory.implementation.AvatarFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AvatarRepositoryTests {

    @Autowired
    AvatarRepository avatarRepository;

    AvatarFactory avatarFactory = new AvatarFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(avatarRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(avatarRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(avatarRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarRepository.save(avatarFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> avatarRepository.save(
                avatarFactory.createValidEntity()
                        .updater()
                        .user(null)
                        .update()
        ));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarRepository.delete(
                Avatar.builder()
                        .id(1L)
                        .build()
        ));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarRepository.delete(
                Avatar.builder()
                        .id(0L)
                        .build()
        ));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> avatarRepository.deleteById(0L));
    }

}
