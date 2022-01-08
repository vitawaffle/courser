package by.vitalylobatsevich.courser.database.entity;

public interface Updater<T extends AppEntity> {

    T update();

}
