package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Role;

public interface RoleService extends CollectionService<Role, Long> {

    boolean existsByName(String name);

}
