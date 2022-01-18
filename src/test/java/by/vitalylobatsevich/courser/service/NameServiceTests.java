package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.application.service.implementation.NameServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.NameRepository;
import by.vitalylobatsevich.courser.factory.NameDTOFactory;
import by.vitalylobatsevich.courser.factory.NameFactory;
import by.vitalylobatsevich.courser.factory.implementation.NameDTOFactoryImpl;
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

    @Mock
    AuthService authService;

    NameFactory nameFactory = new NameFactoryImpl();

    NameDTOFactory nameDTOFactory = new NameDTOFactoryImpl();

    @BeforeEach
    void setUp() {
        nameService = new NameServiceImpl(nameRepository, authService);

        Mockito.lenient()
                .when(nameRepository.findAll())
                .thenReturn(List.of(Name.builder().id(new NameId(1L, 1L)).build()));
        Mockito.lenient()
                .when(nameRepository.findById(new NameId(1L, 1L)))
                .thenReturn(Option.of(Name.builder().id(new NameId(1L, 1L)).build()));
        Mockito.lenient()
                .when(nameRepository.findById(new NameId(0L, 0L)))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(nameRepository.save(nameFactory.createValidEntity()))
                .thenReturn(Name.builder().id(new NameId(1L, 1L)).build());
        Mockito.lenient()
                .doNothing()
                .when(nameRepository)
                .deleteById(new NameId(1L, 1L));
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(nameRepository)
                .deleteById(new NameId(0L, 0L));
        Mockito.lenient()
                .when(nameRepository.findByUser(User.builder().id(1L).build()))
                .thenReturn(List.of(Name.builder().id(new NameId(1L, 1L)).build()));

        Mockito.lenient()
                .when(authService.getUser())
                .thenReturn(User.builder().id(1L).build());
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

    @Test
    void saveForCurrentUser_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.saveForCurrentUser(nameDTOFactory.createValidEntity()));
    }

    @Test
    void getAllForCurrentUser_ShouldReturnNotEmpty() {
        assertFalse(() -> nameService.getAllForCurrentUser().isEmpty());
    }

    @Test
    void deleteByLanguageIdForCurrentUser_ExistingLanguageId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.deleteByLanguageIdForCurrentUser(1L));
    }

    @Test
    void deleteByLanguageIdForCurrentUser_NotExistingLanguageId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.deleteByLanguageIdForCurrentUser(0L));
    }

}
