package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AppRepository<User, Long> {

    boolean existsByEmail(String email);

    Option<User> findByEmail(String email);

}
