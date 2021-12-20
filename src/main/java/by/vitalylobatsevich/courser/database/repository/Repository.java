package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Entity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.util.Streamable;

import java.util.Optional;

@NoRepositoryBean
public interface Repository<T extends Entity, ID> extends org.springframework.data.repository.Repository<T, ID> {

    Optional<T> findById(ID id);

    Streamable<T> findAll();

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

}
