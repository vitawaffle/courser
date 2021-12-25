package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.User;

public interface UserService extends CollectionService<User, Long> {

    boolean existsByEmail(String email);

}
