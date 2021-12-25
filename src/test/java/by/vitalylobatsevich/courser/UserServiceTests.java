package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.application.service.implementation.UserServiceImpl;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
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
    
    UserService testedService;

    @Mock
    UserRepository userRepository;
    
    UserFactory userFactory = new UserFactoryImpl();
    
    String existingEmail = "TestUser1";

    @BeforeEach
    void setUp() {
        testedService = new UserServiceImpl(userRepository);

        Mockito.lenient()
                .when(userRepository.findAll())
                .thenReturn(List.of(userFactory.createEntityWithExistingId()));
        Mockito.lenient()
                .when(userRepository.findById(1L))
                .thenReturn(Option.of(userFactory.createEntityWithExistingId()));
        Mockito.lenient()
                .when(userRepository.save(userFactory.createValidEntity()))
                .thenReturn(userFactory.createValidEntity());
        Mockito.lenient()
                .doNothing()
                .when(userRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(userRepository)
                .deleteById(0L);
        Mockito.lenient()
                .when(userRepository.existsByEmail(existingEmail))
                .thenReturn(true);
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(testedService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(testedService.getById(1L).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedService.save(userFactory.createValidEntity()));
    }

    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedService.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> testedService.deleteById(0L));
    }

    @Test
    void existsByEmail_ExistingEmail_ShouldReturnTrue() {
        assertTrue(testedService.existsByEmail(existingEmail));
    }

}
