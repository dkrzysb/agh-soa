package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;
import pl.agh.kis.soa.model.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.text.SimpleDateFormat;
import java.util.List;

@ManagedBean(name = "OrdersManager")
@SessionScoped
public class OrdersManager {
    private Book orderedBook = new Book();
    private Order order = new Order();
    private String messages;
    private Long clientId;
    private Long bookToReturnId;

    @ManagedProperty(value="#{ordersService}")
    private OrdersService ordersService;

    public Book getOrderedBook() { return orderedBook; }
    public Order getOrder() { return order; }
    public String getMessages() { return messages; }
    public Long getClientId() { return clientId; }
    public List<Order> getOrders() { return ordersService.getAllOrders(); }
    public Long getBookToReturnId() { return bookToReturnId; }

    public void setOrderedBook(Book orderedBook) { this.orderedBook = orderedBook; }
    public void setOrder(Order order) { this.order = order; }
    public void setClientId(Long clientId) { this.clientId = clientId;}
    public void setOrdersService(OrdersService ordersService) { this.ordersService = ordersService; }
    public void setBookToReturnId(Long bookToReturnId) { this.bookToReturnId = bookToReturnId; }

    public List<Order> getClientActiveOrders(Long clientId) {
        return ordersService.getClientActiveOrders(clientId);
    }

    public String order() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (orderedBook.getId() == null) {
                messages = "You have to pick one book";
            } else if (formatter.parse(formatter.format(order.getStartDate())).compareTo(formatter.parse(formatter.format(order.getEndDate()))) > 0) {
                messages = "Start date has to be lower than end date";
            } else {
                messages = ordersService.order(orderedBook, order, clientId);
            }
        }
        catch(Exception ex) {
            messages = "Internal error!";
        }

        order = new Order();
        orderedBook = new Book();

        return "order";
    }

    public String returnBook(Long clientId) {
        ordersService.returnBook(clientId, bookToReturnId);

        return "order";
    }
}
