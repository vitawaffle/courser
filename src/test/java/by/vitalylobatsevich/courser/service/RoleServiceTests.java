package by.vitalylobatsevich.courser.service;

import by.vitalylobatsevich.courser.application.service.RoleService;
import by.vitalylobatsevich.courser.application.service.implementation.RoleServiceImpl;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.repository.RoleRepository;
import by.vitalylobatsevich.courser.factory.RoleFactory;
import by.vitalylobatsevich.courser.factory.implementation.RoleFactoryImpl;

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
class RoleServiceTests {

    RoleService roleService;

    @Mock
    RoleRepository roleRepository;

    RoleFactory roleFactory = new RoleFactoryImpl();

    @BeforeEach
    void setUp() {
        roleService = new RoleServiceImpl(roleRepository);

        Mockito.lenient()
                .when(roleRepository.findAll())
                .thenReturn(List.of(Role.roleBuilder().id(1L).build()));
        Mockito.lenient()
                .when(roleRepository.findById(1L))
                .thenReturn(Option.of(Role.roleBuilder().id(1L).build()));
        Mockito.lenient()
                .when(roleRepository.findById(0L))
                .thenReturn(Option.none());
        Mockito.lenient()
                .when(roleRepository.save(roleFactory.createValidEntity()))
                .thenReturn(Role.roleBuilder().id(1L).build());
        Mockito.lenient()
                .doNothing()
                .when(roleRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(roleRepository)
                .deleteById(0L);
        Mockito.lenient()
                .when(roleRepository.existsByName("TEST_ROLE_1"))
                .thenReturn(true);
        Mockito.lenient()
                .when(roleRepository.existsByName(""))
                .thenReturn(false);
    }

    @Test
    void getAll_ShouldReturnNotEmpty() {
        assertFalse(roleService.getAll().isEmpty());
    }

    @Test
    void getById_ExistingId_ShouldReturnNotEmpty() {
        assertFalse(roleService.getById(1L).isEmpty());
    }

    @Test
    void getById_NotExistingId_ShouldReturnEmpty() {
        assertTrue(roleService.getById(0L).isEmpty());
    }

    @Test
    void save_ValidEntity_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleService.save(roleFactory.createValidEntity()));
    }

    @Test
    void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleService.deleteById(1L));
    }

    @Test
    void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleService.deleteById(0L));
    }

    @Test
    void existsByName_ExistingName_ShouldReturnTrue() {
        assertTrue(roleService.existsByName("TEST_ROLE_1"));
    }

    @Test
    void existsByName_NotExistingName_ShouldReturnFalse() {
        assertFalse(roleService.existsByName(""));
    }

}
