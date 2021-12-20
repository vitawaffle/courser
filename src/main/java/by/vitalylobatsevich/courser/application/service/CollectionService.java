package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Entity;

import org.springframework.data.util.Streamable;

import java.util.Optional;

public interface CollectionService<T extends Entity, ID> extends Service {

    Streamable<T> getAll();

    Optional<T> getById(ID id);

    T save(T t);

    void deleteById(ID id);

}
