package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.AppEntity;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface AppRepository<T extends AppEntity, ID> extends Repository<T, ID> {

    Option<T> findById(ID id);

    Seq<T> findAll();

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

}
