package pl.agh.kis.soa;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Book {
    private Long id;
    private String title;
    private String author;
    private BookType type;
    private BigDecimal price;
    private String currency;
    private int pages;

    public Book(Long id, String title, String author, BookType type, BigDecimal price, String currency, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pages = pages;
    }

    public Book(Book book) {
        this.id = book.id;
        this.title = book.title;
        this.author = book.author;
        this.type = book.type;
        this.price = new BigDecimal(book.price.doubleValue()).setScale(2, RoundingMode.HALF_UP);
        this.currency = book.currency;
        this.pages = book.pages;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public BookType getType() { return type; }
    public BigDecimal getPrice() { return price; }
    public String getCurrency() { return currency; }
    public int getPages() { return pages; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setType(BookType type) { this.type = type; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setPages(int pages) { this.pages = pages; }
}

enum BookType {
    All,
    War,
    Romance,
    Criminal,
    Fantasy,
    Novel
}

enum Columns {
    Title,
    Author,
    Type,
    Price,
    Currency,
    Pages
}