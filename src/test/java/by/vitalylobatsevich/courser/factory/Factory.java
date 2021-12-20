package by.vitalylobatsevich.courser.factory;

public interface Factory<T> {

    T createValidEntity();

    T createEntityWithExistingId();

}
