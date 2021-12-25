package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long> {

    boolean existsByEmail(String email);

    Option<User> findByEmail(String email);

}
