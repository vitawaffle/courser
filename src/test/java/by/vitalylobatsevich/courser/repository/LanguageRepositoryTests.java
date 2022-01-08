package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.repository.LanguageRepository;

import by.vitalylobatsevich.courser.factory.LanguageFactory;
import by.vitalylobatsevich.courser.factory.implementation.LanguageFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LanguageRepositoryTests {

    @Autowired
    LanguageRepository languageRepository;

    LanguageFactory languageFactory = new LanguageFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(languageRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(languageRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(languageRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageRepository.save(languageFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> languageRepository.save(
                languageFactory.createValidEntity().updater().code(null).update()));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageRepository.delete(Language.builder().id(1L).build()));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageRepository.delete(Language.builder().id(0L).build()));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> languageRepository.deleteById(0L));
    }

    @Test
    void existsByCode_ExistingCode_ShouldReturnTrue() {
        assertTrue(languageRepository.existsByCode("test1"));
    }

    @Test
    void existsByCode_NotExistingCode_ShouldReturnFalse() {
        assertFalse(languageRepository.existsByCode(""));
    }

    @Test
    void existsById_ExistingId_ShouldReturnTrue() {
        assertTrue(languageRepository.existsById(1L));
    }

    @Test
    void existsById_NotExistingId_ShouldReturnFalse() {
        assertFalse(languageRepository.existsById(0L));
    }

}
