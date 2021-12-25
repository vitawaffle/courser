package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.Entity;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

public interface CollectionService<T extends Entity, ID> extends Service {

    Seq<T> getAll();

    Option<T> getById(ID id);

    T save(T t);

    void deleteById(ID id);

}
