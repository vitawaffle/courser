package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.application.service.implementation.UserServiceImpl;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.UserFactory;
import by.vitalylobatsevich.courser.factory.implementation.UserFactoryImpl;

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
class UserServiceTests {
    
    UserService userService;

    @Mock
    UserRepository userRepository;
    
    UserFactory userFactory = new UserFactoryImpl();

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);

        Mockito.lenient()
                .when(userRepository.findAll())
                .thenReturn(List.of(User.userBuilder().id(1L).build()));
        Mockito.lenient()
                .when(userRepository.findById(1L))
                .thenReturn(Option.of(User.userBuilder().id(1L).build()));
        Mockito.lenient()
                .when(userRepository.findById(0L))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(userRepository.save(userFactory.createValidEntity()))
                .thenReturn(User.userBuilder().id(1L).build());
        Mockito.lenient()
                .doNothing()
                .when(userRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(userRepository)
                .deleteById(0L);
        Mockito.lenient()
                .when(userRepository.existsByEmail("test.email1@test.org"))
                .thenReturn(true);
        Mockito.lenient()
                .when(userRepository.existsByEmail(""))
                .thenReturn(false);
        Mockito.lenient()
                .when(userRepository.findByEmail("test.email1@test.org"))
                .thenReturn(Option.of(User.userBuilder().id(1L).build()));
        Mockito.lenient()
                .when(userRepository.findByEmail(""))
                .thenReturn(Option.none());
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(userService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(userService.getById(1L).isEmpty());
    }

    @Test
    void getById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(userService.getById(0L).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userService.save(userFactory.createValidEntity()));
    }

    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userService.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userService.deleteById(0L));
    }

    @Test
    void existsByEmail_ExistingEmail_ShouldReturnTrue() {
        assertTrue(userService.existsByEmail("test.email1@test.org"));
    }

    @Test
    void existsByEmail_NotExistingEmail_ShouldReturnFalse() {
        assertFalse(userService.existsByEmail(""));
    }

    @Test
    void getByEmail_ExistingEmail_ShouldReturnNotEmpty() {
        assertFalse(userService.getByEmail("test.email1@test.org").isEmpty());
    }

    @Test
    void getByEmail_NotExistingEmail_ShouldReturnEmpty() {
        assertTrue(userService.getByEmail("").isEmpty());
    }

}
