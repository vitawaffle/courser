package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;

import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends CourserRepository<Name, NameId> {
}
