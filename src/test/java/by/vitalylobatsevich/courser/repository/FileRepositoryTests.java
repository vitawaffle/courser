package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.File;
import by.vitalylobatsevich.courser.database.repository.FileRepository;
import by.vitalylobatsevich.courser.factory.FileFactory;
import by.vitalylobatsevich.courser.factory.implementation.FileFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileRepositoryTests {

    @Autowired
    FileRepository fileRepository;

    FileFactory fileFactory = new FileFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(fileRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(fileRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(fileRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> fileRepository.save(fileFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(
                Exception.class,
                () -> fileRepository.save(
                        fileFactory.createValidEntity()
                                .updater()
                                .path(null)
                                .update()
                )
        );
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> fileRepository.delete(File.builder().id(1L).build()));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> fileRepository.delete(File.builder().id(0L).build()));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> fileRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> fileRepository.deleteById(0L));
    }

}
