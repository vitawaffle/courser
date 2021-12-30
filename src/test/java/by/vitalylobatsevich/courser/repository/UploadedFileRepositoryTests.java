package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.UploadedFile;
import by.vitalylobatsevich.courser.database.repository.UploadedFileRepository;
import by.vitalylobatsevich.courser.factory.UploadedFileFactory;
import by.vitalylobatsevich.courser.factory.implementation.UploadedFileFactoryImpl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UploadedFileRepositoryTests {

    @Autowired
    UploadedFileRepository uploadedFileRepository;

    UploadedFileFactory uploadedFileFactory = new UploadedFileFactoryImpl();

    @Test
    void findAll_ShouldReturnNotEmpty() {
        assertFalse(uploadedFileRepository.findAll().isEmpty());
    }

    @Test
    void findById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(uploadedFileRepository.findById(1L).isEmpty());
    }

    @Test
    void findById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(uploadedFileRepository.findById(0L).isEmpty());
    }

    @Test
    @Transactional
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> uploadedFileRepository.save(uploadedFileFactory.createValidEntity()));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(
                Exception.class,
                () -> uploadedFileRepository.save(uploadedFileFactory.createValidEntity().updatePath(null))
        );
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> uploadedFileRepository.delete(new UploadedFile(1L)));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> uploadedFileRepository.delete(new UploadedFile(0L)));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> uploadedFileRepository.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(EmptyResultDataAccessException.class, () -> uploadedFileRepository.deleteById(0L));
    }

}
