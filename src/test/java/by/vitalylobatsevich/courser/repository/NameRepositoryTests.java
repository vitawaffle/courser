package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.repository.NameRepository;
import by.vitalylobatsevich.courser.factory.NameFactory;
import by.vitalylobatsevich.courser.factory.implementation.NameFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameRepositoryTests {

    @Autowired
    NameRepository nameRepository;

    NameFactory nameFactory = new NameFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(nameRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(nameRepository.findById(new NameId(1L, 1L)).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(nameRepository.findById(new NameId(0L, 0L)).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.save(nameFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> nameRepository.save(
                nameFactory.createValidEntity().updater().nameId(null).language(null).user(null).update()
        ));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.delete(Name.builder().id(new NameId(1L, 1L)).build()));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.delete(Name.builder().id(new NameId(0L, 0L)).build()));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.deleteById(new NameId(1L, 1L)));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class,
                     () -> nameRepository.deleteById(new NameId(0L, 0L)));
    }

}
