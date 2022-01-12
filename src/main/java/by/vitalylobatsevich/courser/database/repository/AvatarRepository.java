package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Avatar;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends AppRepository<Avatar, Long> {
}
