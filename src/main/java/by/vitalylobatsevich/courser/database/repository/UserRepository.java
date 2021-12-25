package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.User;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long> {

    boolean existsByEmail(String email);

}
