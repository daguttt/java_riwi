package models;

import java.util.List;

public interface Crud<T> {
    T create(T object);
    List<T> findAll();
    T findById( int id);
    boolean update(T object, int id);
    boolean delete(int id);
}