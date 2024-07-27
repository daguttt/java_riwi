package entities;

import java.sql.Date;

public class Book {
    private Integer id = null;
    private String title;
    private Date publishedDate;
    private float price;
    private final int authorsId;

    public Book(Integer id, String title, Date publishedDate, float price, int authorsId) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.price = price;
        this.authorsId = authorsId;
    }

    public Book(String title, Date publishedDate, float price, int authorsId) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.price = price;
        this.authorsId = authorsId;
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

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAuthorsId() {
        return authorsId;
    }

    @Override
    public String toString() {
        var lines = new String[] {
                String.format("id: %d", this.getId()),
                String.format("title: %s", this.getTitle()),
                String.format("published_date: %s", this.getPublishedDate()),
                String.format("price: %s", this.getPrice()),
        };
        return String.join("\n", lines);
    }
}
