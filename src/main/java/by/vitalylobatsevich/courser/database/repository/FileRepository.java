package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.File;

import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends AppRepository<File, Long> {
}
