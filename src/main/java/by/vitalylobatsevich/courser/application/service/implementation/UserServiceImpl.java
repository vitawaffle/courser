package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Streamable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(final Long id) {
        try {
            userRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException ignore) {
        }
    }

    @Override
    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

}
