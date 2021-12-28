package by.vitalylobatsevich.courser.application.service.implementation;

import by.vitalylobatsevich.courser.application.service.UserService;
import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.database.repository.UserRepository;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Seq<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Option<User> getById(final Long id) {
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

    @Override
    public Option<User> getByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

}
