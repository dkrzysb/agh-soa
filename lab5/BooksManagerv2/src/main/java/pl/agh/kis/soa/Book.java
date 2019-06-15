package pl.agh.kis.soa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "book")
public class Book {
    private Long id;
    private String title;
    private String author;
    private BookType type;
    private BigDecimal price;
    private String currency;
    private int pages;

    public Book() {
        super();
    }

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

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() { return id; }
    @Column(name = "title", nullable = false)
    public String getTitle() { return title; }
    @Column(name = "author")
    public String getAuthor() { return author; }
    @Column(name ="type", nullable = false)
    public BookType getType() { return type; }
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() { return price; }
    @Column(name = "currency", nullable = false)
    public String getCurrency() { return currency; }
    @Column(name = "pages", nullable = false)
    public int getPages() { return pages; }

    public void setId(Long id) { this.id = id; }
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