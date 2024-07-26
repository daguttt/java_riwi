package controllers;

import models.BooksModel;

public class BooksController {
    private final BooksModel booksModel;

    public BooksController(BooksModel booksModel) {
        this.booksModel = booksModel;
    }

    public void create() {}
    public void findAll() {}
    public void findById() {}
    public void update() {}
    public void delete() {}
    public void findAllByAuthorId() {}
}
