package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.control.Option;

public interface UserService extends CollectionService<User, Long> {

    boolean existsByEmail(String email);

    Option<User> getByEmail(String email);

}
