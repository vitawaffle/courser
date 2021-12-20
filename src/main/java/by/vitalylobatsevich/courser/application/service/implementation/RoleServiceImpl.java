package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.RoleService;
import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.database.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Streamable<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getById(final Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(final Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public boolean existsByName(final String name) {
        return roleRepository.existsByName(name);
    }

}
