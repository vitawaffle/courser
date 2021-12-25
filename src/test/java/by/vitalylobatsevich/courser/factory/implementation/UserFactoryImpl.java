package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.User;
import by.vitalylobatsevich.courser.factory.UserFactory;

import lombok.val;

public class UserFactoryImpl implements UserFactory {

    @Override
    public User createValidEntity() {
        val user = new User();
        user.setEmail("ValidUsername");
        user.setPassword("$2a$10$QXpsri3rlTeo.BlbIDpAZOhHo5pZEI.Sw.pg9Ts7hjU.YQ.8nCM6");
        return user;
    }

    @Override
    public User createEntityWithExistingId() {
        val user = new User();
        user.setEmail("TestUser1");
        user.setPassword("$2a$10$QXpsri3rlTeo.BlbIDpAZOhHo5pZEI.Sw.pg9Ts7hjU.YQ.8nCM6");
        return user;
    }

}
