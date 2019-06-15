package pl.agh.kis.soa.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@NamedQuery(query = "select c from Client c", name = "get all clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, unique = true)
    private Long id;
    private String name;
    private String surname;
    private String emailAddress;
    private boolean notifications;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Client() { }
    public Client(String name, String surname, String emailAddress, boolean notifications) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.notifications = notifications;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmailAddress() { return emailAddress; }
    public boolean getNotifications() { return notifications; }
    public List<Order> getOrders() { return orders; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }

    public void addOrder(Order order) {
        order.setClient(this);
        orders.add(order);
    }

    public void removeOrder(Order order) {
        order.setClient(null);
        orders.remove(order);
    }
}
