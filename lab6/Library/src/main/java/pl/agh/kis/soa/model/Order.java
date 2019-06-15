package pl.agh.kis.soa.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_books")
@NamedQuery(query = "select o from Order o", name = "get all orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    public Order() { }
    public Order(Client client, Book book, Date startDate, Date endDate) {
        this.client = client;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }
    public Client getClient() { return client; }
    public Book getBook() { return book; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    public void setClient(Client client) { this.client = client; }
    public void setBook(Book book) { this.book = book; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
