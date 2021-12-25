package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.Entity;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repository<T extends Entity, ID> extends org.springframework.data.repository.Repository<T, ID> {

    Option<T> findById(ID id);

    Seq<T> findAll();

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

}
