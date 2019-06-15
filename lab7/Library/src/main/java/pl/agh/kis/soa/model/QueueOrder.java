package pl.agh.kis.soa.model;

import javax.persistence.*;

@Entity
@Table(name = "queue_order")
public class QueueOrder {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public QueueOrder() { }
    public QueueOrder(Book book, Client client) {
        this.book = book;
        this.client = client;
    }

    public Long getId() { return id; }
    public Book getBook() { return book; }
    public Client getClient() { return client; }

    public void setId(Long id) { this.id = id; }
    public void setBook(Book book) { this.book = book; }
    public void setClient(Client client) { this.client = client; }
}
