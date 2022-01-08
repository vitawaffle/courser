package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.NameService;
import by.vitalylobatsevich.courser.application.service.implementation.NameServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.NameRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NameServiceTests {

    NameService nameService;

    @Mock
    NameRepository nameRepository;

    @Mock
    UserRepository userRepository;

    NameFactory nameFactory = new NameFactoryImpl();

    NameDTOFactory nameDTOFactory = new NameDTOFactoryImpl();

    @BeforeEach
    void setUp() {
        nameService = new NameServiceImpl(nameRepository, userRepository);

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
                .when(userRepository.findByEmail("test.email@test.org"))
                .thenReturn(Option.of(User.builder().id(1L).build()));
        Mockito.lenient()
                .when(userRepository.findByEmail(""))
                .thenReturn(Option.none());
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
    void save_ExistingUsername_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> nameService.save(nameDTOFactory.createValidEntity(), "test.email@test.org"));
    }

    @Test
    void save_NotExistingUsername_ShouldThrowsUsernameNotFoundException() {
        assertThrows(UsernameNotFoundException.class,
                     () -> nameService.save(nameDTOFactory.createValidEntity(), ""));
    }

}
