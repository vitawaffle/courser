package by.vitalylobatsevich.courser.database.entity;

public interface Updater<T extends CourserEntity> {

    T update();

}
