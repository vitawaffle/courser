package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.UserFactory;

import java.util.Collections;

public class UserFactoryImpl implements UserFactory {

    @Override
    public User createValidEntity() {
        return User.userBuilder()
                .email("test.email@test.org")
                .password("$2a$10$9FtBoW7.ciiSQ.VYlpxG8O7NCDIEEMmHgkX42DDDX9y/AP1tX5KvG") // TestPassword123
                .roles(Collections.emptyList())
                .build();
    }

}
