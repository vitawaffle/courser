package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.factory.RoleFactory;

public class RoleFactoryImpl implements RoleFactory {

    @Override
    public Role createValidEntity() {
        return Role.roleBuilder()
                .name("TEST_ROLE")
                .build();
    }

}
