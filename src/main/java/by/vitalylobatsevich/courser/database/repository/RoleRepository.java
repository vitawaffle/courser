package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Role;

@org.springframework.stereotype.Repository
public interface RoleRepository extends Repository<Role, Long> {

    boolean existsByName(String name);

}
