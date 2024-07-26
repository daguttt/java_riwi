package entities;

import java.time.LocalDate;

public class Book {
    private Integer id = null;
    private String title;
    private LocalDate publishedDate;
    private float price;
    private final int authorId;

    public Book(Integer id, String title, LocalDate publishedDate, float price, int authorId) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.price = price;
        this.authorId = authorId;
    }

    public Book(String title, LocalDate publishedDate, float price, int authorId) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.price = price;
        this.authorId = authorId;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }
}
