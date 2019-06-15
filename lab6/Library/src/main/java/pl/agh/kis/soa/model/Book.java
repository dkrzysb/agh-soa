package pl.agh.kis.soa.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, unique = true)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private int pages;

    public Book() { }
    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
    public Book(Book book) {
        this.id = book.id;
        this.title = book.title;
        this.author = book.author;
        this.pages = book.pages;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public int getPages() { return pages; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(Author author) { this.author = author; }
    public void setPages(int pages) { this.pages = pages; }
}