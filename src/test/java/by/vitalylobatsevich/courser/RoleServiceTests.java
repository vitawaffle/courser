package by.vitalylobatsevich.courser;

import by.vitalylobatsevich.courser.application.service.RoleService;
import by.vitalylobatsevich.courser.application.service.implementation.RoleServiceImpl;
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

    RoleService testedService;

    @Mock
    RoleRepository roleRepository;

    RoleFactory roleFactory = new RoleFactoryImpl();

    String existingName = "TEST_ROLE_1";

    @BeforeEach
    void setUp() {
        testedService = new RoleServiceImpl(roleRepository);

        Mockito.lenient()
                .when(roleRepository.findAll())
                .thenReturn(List.of(roleFactory.createEntityWithExistingId()));
        Mockito.lenient()
                .when(roleRepository.findById(1L))
                .thenReturn(Option.of(roleFactory.createEntityWithExistingId()));
        Mockito.lenient()
                .when(roleRepository.save(roleFactory.createValidEntity()))
                .thenReturn(roleFactory.createValidEntity());
        Mockito.lenient()
                .doNothing()
                .when(roleRepository)
                .deleteById(1L);
        Mockito.lenient()
                .doThrow(new EmptyResultDataAccessException(1))
                .when(roleRepository)
                .deleteById(0L);
        Mockito.lenient()
                .when(roleRepository.existsByName(existingName))
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
        assertDoesNotThrow(() -> testedService.save(roleFactory.createValidEntity()));
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
        assertTrue(testedService.existsByName(existingName));
    }

}
