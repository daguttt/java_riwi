package models;

import entities.Author;
import persistence.Database;

import java.util.List;

public class BooksModel implements Crud<Author> {
    private Database database;

    public BooksModel(Database database) {
        this.database = database;
    }

    @Override
    public Author create(Author object) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return List.of();
    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public boolean update(Author object, int id) {
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
