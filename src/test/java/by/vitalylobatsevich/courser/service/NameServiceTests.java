package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.application.service.implementation.NameServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.repository.NameRepository;
import by.vitalylobatsevich.courser.factory.NameFactory;
import by.vitalylobatsevich.courser.factory.implementation.NameFactoryImpl;

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
class NameServiceTests {

    NameService nameService;

    @Mock
    NameRepository nameRepository;

    NameFactory nameFactory = new NameFactoryImpl();

    @BeforeEach
    void setUp() {
        nameService = new NameServiceImpl(nameRepository);

        Mockito.lenient()
                .when(nameRepository.findAll())
                .thenReturn(List.of(Name.nameBuilder().id(new NameId(1L, 1L)).build()));
        Mockito.lenient()
                .when(nameRepository.findById(new NameId(1L, 1L)))
                .thenReturn(Option.of(Name.nameBuilder().id(new NameId(1L, 1L)).build()));
        Mockito.lenient()
                .when(nameRepository.findById(new NameId(0L, 0L)))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(nameRepository.save(nameFactory.createValidEntity()))
                .thenReturn(Name.nameBuilder().id(new NameId(1L, 1L)).build());
        Mockito.lenient()
                .doNothing()
                .when(nameRepository)
                .deleteById(new NameId(1L, 1L));
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(nameRepository)
                .deleteById(new NameId(0L, 0L));
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(nameService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(nameService.getById(new NameId(1L, 1L)).isEmpty());
    }

    @Test
    void getById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(nameService.getById(new NameId(0L, 0L)).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.save(nameFactory.createValidEntity()));
    }

    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.deleteById(new NameId(1L, 1L)));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.deleteById(new NameId(0L, 0L)));
    }

}
