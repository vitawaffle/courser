package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Name;
import by.vitalylobatsevich.courser.database.entity.NameId;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.collection.Seq;

import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends AppRepository<Name, NameId> {

    Seq<Name> findByUser(User user);

}
