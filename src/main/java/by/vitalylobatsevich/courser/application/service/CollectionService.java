package by.vitalylobatsevich.courser.application.service;

import by.vitalylobatsevich.courser.database.entity.AppEntity;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

public interface CollectionService<T extends AppEntity, ID> extends AppService {

    Seq<T> getAll();

    Option<T> getById(ID id);

    T save(T t);

    void delete(T t);

    void deleteById(ID id);

}
