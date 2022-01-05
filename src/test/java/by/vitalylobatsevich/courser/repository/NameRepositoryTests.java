package by.vitalylobatsevich.courser.repository;

import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.NameRepository;

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
        assertDoesNotThrow(() -> nameRepository.save(
                Name.nameBuilder()
                        .nameId(new NameId())
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .patronymic("Test Patronymic")
                        .language(Language.languageBuilder().id(1L).build())
                        .user(User.userBuilder().id(2L).build())
                        .build()
        ));
    }

    @Test
    void save_NotValidEntity_ShouldThrowsException() {
        assertThrows(Exception.class, () -> nameRepository.save(
                Name.nameBuilder()
                        .firstName("Test First Name")
                        .lastName("Test Last Name")
                        .patronymic("Test Patronymic")
                        .build()
        ));
    }

    @Test
    @Transactional
    void delete_EntityWithExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.delete(
                Name.nameBuilder()
                        .nameId(new NameId(1L, 1L))
                        .build()
        ));
    }

    @Test
    void delete_EntityWithNotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.delete(
                Name.nameBuilder()
                        .nameId(new NameId(0L, 0L))
                        .build()
        ));
    }

    @Test
    @Transactional
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameRepository.deleteById(new NameId(1L, 1L)));
    }

    @Test
    void deleteById_NotExistingId_ShouldThrowsEmptyResultDataAccessException() {
        assertThrows(
                EmptyResultDataAccessException.class,
                () -> nameRepository.deleteById(new NameId(0L, 0L))
        );
    }

}
