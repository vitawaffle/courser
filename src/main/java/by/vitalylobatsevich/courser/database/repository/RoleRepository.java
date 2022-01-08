package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Role;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends AppRepository<Role, Long> {

    boolean existsByName(String name);

}
