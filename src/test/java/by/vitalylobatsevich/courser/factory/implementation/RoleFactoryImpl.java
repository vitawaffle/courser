package by.vitalylobatsevich.courser.factory.implementation;

import by.vitalylobatsevich.courser.database.entity.Role;
import by.vitalylobatsevich.courser.factory.RoleFactory;
import lombok.val;

public class RoleFactoryImpl implements RoleFactory {

    @Override
    public Role createValidEntity() {
        val role = new Role();
        role.setName("VALID_ROLE");
        return role;
    }

    @Override
    public Role createEntityWithExistingId() {
        val role = new Role();
        role.setId(1L);
        return role;
    }

}
