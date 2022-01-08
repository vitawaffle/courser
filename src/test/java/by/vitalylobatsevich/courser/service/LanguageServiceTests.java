package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.LanguageService;
import by.vitalylobatsevich.courser.application.service.implementation.LanguageServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Language;
import by.vitalylobatsevich.courser.database.repository.LanguageRepository;
import by.vitalylobatsevich.courser.factory.LanguageFactory;
import by.vitalylobatsevich.courser.factory.implementation.LanguageFactoryImpl;

import io.vavr.collection.List;
import io.vavr.control.Option;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTests {

    LanguageService languageService;

    @Mock
    LanguageRepository languageRepository;

    LanguageFactory languageFactory = new LanguageFactoryImpl();

    @BeforeEach
    void setUp() {
        languageService = new LanguageServiceImpl(languageRepository);

        Mockito.lenient()
                .when(languageRepository.findAll())
                .thenReturn(List.of(Language.builder().id(1L).build()));
        Mockito.lenient()
                .when(languageRepository.findById(1L))
                .thenReturn(Option.of(Language.builder().id(1L).build()));
        Mockito.lenient()
                .when(languageRepository.findById(0L))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(languageRepository.save(languageFactory.createValidEntity()))
                .thenReturn(Language.builder().id(1L).build());
        Mockito.lenient()
                .doNothing()
                .when(languageRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(languageRepository)
                .deleteById(0L);
        Mockito.lenient()
                .when(languageRepository.existsByCode("test1"))
                .thenReturn(true);
        Mockito.lenient()
                .when(languageRepository.existsByCode(""))
                .thenReturn(false);
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(languageService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(languageService.getById(1L).isEmpty());
    }

    @Test
    void getById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(languageService.getById(0L).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageService.save(languageFactory.createValidEntity()));
    }
    
    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageService.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> languageService.deleteById(0L));
    }

    @Test
    void existsByCode_ExistingCode_ShouldReturnTrue() {
        assertTrue(languageService.existsByCode("test1"));
    }

    @Test
    void existsByCode_NotExistingCode_ShouldReturnFalse() {
        assertFalse(languageService.existsByCode(""));
    }

}
