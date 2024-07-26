package models;

import java.util.List;

public interface Crud<T> {
    public T create(T object);
    public List<T> findAll();
    public T findById( int id);
    public boolean update(T object, int id);
    public boolean delete(int id);
}