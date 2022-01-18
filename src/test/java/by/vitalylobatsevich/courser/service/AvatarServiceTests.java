package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.AuthService;
import by.vitalylobatsevich.courser.application.service.AvatarService;
import by.vitalylobatsevich.courser.application.service.FileService;
import by.vitalylobatsevich.courser.application.service.StorageService;
import by.vitalylobatsevich.courser.application.service.implementation.AvatarServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.repository.AvatarRepository;
import by.vitalylobatsevich.courser.database.repository.UserRepository;
import by.vitalylobatsevich.courser.factory.AvatarFactory;
import by.vitalylobatsevich.courser.factory.implementation.AvatarFactoryImpl;

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
public class AvatarServiceTests {

    AvatarService avatarService;

    @Mock
    AvatarRepository avatarRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    FileService fileService;

    @Mock
    AuthService authService;

    @Mock
    StorageService storageService;

    AvatarFactory avatarFactory = new AvatarFactoryImpl();

    @BeforeEach
    void setUp() {
        avatarService = new AvatarServiceImpl(
                avatarRepository,
                userRepository,
                fileService,
                authService,
                storageService
        );

        Mockito.lenient()
                .when(avatarRepository.findAll())
                .thenReturn(List.of(Avatar.builder().id(1L).build()));
        Mockito.lenient()
                .when(avatarRepository.findById(1L))
                .thenReturn(Option.of(Avatar.builder().id(1L).build()));
        Mockito.lenient()
                .when(avatarRepository.findById(0L))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(avatarRepository.save(avatarFactory.createValidEntity()))
                .thenReturn(Avatar.builder().id(1L).build());
        Mockito.lenient()
                .doNothing()
                .when(avatarRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(avatarRepository)
                .deleteById(0L);
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(avatarService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(avatarService.getById(1L).isEmpty());
    }

    @Test
    void getById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(avatarService.getById(0L).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarService.save(avatarFactory.createValidEntity()));
    }

    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarService.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> avatarService.deleteById(0L));
    }

}
