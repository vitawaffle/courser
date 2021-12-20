package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.application.service.implementation.UserServiceImpl;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.factory.UserFactory;
import by.vitalylobatsevich.courser.factory.implementation.UserFactoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Streamable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
    
    UserService testedService;

    @Mock
    UserRepository userRepository;
    
    UserFactory userFactory = new UserFactoryImpl();
    
    String existingUsername = "TestUser1";

    @BeforeEach
    void setUp() {
        testedService = new UserServiceImpl(userRepository);

        Mockito.lenient()
                .when(userRepository.findAll())
                .thenReturn(Streamable.of(userFactory.createEntityWithExistingId()));
        Mockito.lenient()
                .when(userRepository.findById(1L))
                .thenReturn(Optional.of(userFactory.createEntityWithExistingId()));
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
                .when(userRepository.existsByUsername(existingUsername))
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
    void existsByName_ExistingName_ShouldReturnTrue() {
        assertTrue(testedService.existsByUsername(existingUsername));
    }

}
