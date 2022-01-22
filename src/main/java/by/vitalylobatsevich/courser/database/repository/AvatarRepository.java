package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import by.vitalylobatsevich.courser.database.entity.User;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends AppRepository<Avatar, Long> {

    Option<Avatar> findByIdAndUser(Long id, User user);

    Seq<Avatar> findByUser(User user);

    void deleteByIdAndUser(Long id, User user);

}
