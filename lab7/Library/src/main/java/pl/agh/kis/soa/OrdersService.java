package pl.agh.kis.soa;

import org.json.JSONObject;
import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;
import pl.agh.kis.soa.model.Order;
import pl.agh.kis.soa.model.QueueOrder;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class OrdersService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();
    @Inject
    private MessagesSender messagesSender;

    public String order(Book book, Order order, Long clientId) {
        EntityManager em = factory.createEntityManager();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String jqpl = "select o from Order o WHERE o.book.id = :id";
        Query query = em.createQuery(jqpl, Order.class)
                .setParameter("id", book.getId());
        Query clientQuery = em.createQuery("select c from Client c where c.id = :id", Client.class)
                .setParameter("id", clientId);
        Client client = (Client)clientQuery.getSingleResult();

        try {
            Date orderStartDate = formatter.parse(formatter.format(order.getStartDate()));
            Date orderEndDate = formatter.parse(formatter.format(order.getEndDate()));

        }
        catch(Exception ex) {
            System.err.println("Error parsing dates: " + ex.getMessage());
            return "Internal error!";
        }

        if(query.getResultList().size() != 0) {
            Order dbOrder = (Order)query.getResultList().get(query.getResultList().size() - 1);
            Date now = new Date();
            Date endDate = null;
            try {
                now = formatter.parse(formatter.format(now));
                endDate = formatter.parse(formatter.format(dbOrder.getEndDate()));

                if (now.compareTo(endDate) < 0) {
                    QueueOrder queueOrder = new QueueOrder(book, client);
                    em.getTransaction().begin();
                    em.persist(queueOrder);
                    em.getTransaction().commit();
                    return "This book is already reserved. Predicted return date: " + formatter.parse(formatter.format(((Order) query.getResultList().stream().findFirst().get()).getEndDate()));
                }
            }
            catch(Exception ex) {
                System.err.println("Error parsing date: " + ex.getMessage());
                return "Internal error!";
            }
        }

        try {
            order.setStartDate(formatter.parse(formatter.format(order.getStartDate())));
            order.setEndDate(formatter.parse(formatter.format(order.getEndDate())));
        }
        catch(Exception ex) {
            System.err.println("Error parsing date: " + ex.getMessage());
        }

        try {
            em.getTransaction().begin();
            order.setBook(book);
            client.addOrder(order);
            em.persist(order);
            em.getTransaction().commit();

            return "Success!";
        }
        catch(Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error in adding record: " + ex.getMessage());
            return "Internal error!";
        }
    }

    public List<Order> getAllOrders() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Order> query = em.createNamedQuery("get all orders", Order.class);
        return query.getResultList();
    }

    public List<Order> getClientActiveOrders(Long clientId) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Predicate criteria = cb.conjunction();
        Root<Order> fromOrder = query.from(Order.class);
        Join<Order, Client> joinClient = fromOrder.join("client");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        criteria = cb.and(criteria, cb.equal(joinClient.get("id"), clientId));
        try {
            criteria = cb.and(criteria, cb.greaterThan(fromOrder.get("endDate"), formatter.parse(formatter.format(new Date()))));
        }
        catch(Exception ex) {
            System.err.println("Error parsing date: " + ex.getMessage());
            return null;
        }
        query.select(fromOrder).where(criteria);
        TypedQuery<Order> tq = em.createQuery(query);
        return tq.getResultList();
    }

    public void returnBook(Long clientId, Long bookId) {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select o from Order o where o.book.id = :bid and o.client.id = :cid", Order.class)
                .setParameter("bid", bookId)
                .setParameter("cid", clientId);
        Query ordersQueueQuery = em.createQuery("select qo from QueueOrder qo where qo.book.id = :bid", QueueOrder.class)
                .setParameter("bid", bookId);
        Order order = (Order)query.getResultList().get(query.getResultList().size() - 1);
        List<QueueOrder> queueOrders = ordersQueueQuery.getResultList();
        for(QueueOrder queueOrder : queueOrders) {
            JSONObject jobject = new JSONObject();
            jobject.put("clientEmail", queueOrder.getClient().getEmailAddress());
            jobject.put("notifications", queueOrder.getClient().getNotifications());
            jobject.put("notificationType", "bookAvailable");
            jobject.put("bookTitle", order.getBook().getTitle());
            jobject.put("bookAuthor", order.getBook().getAuthor().getName());
            messagesSender.sendMessage(jobject.toString());
            em.remove(queueOrder);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            em.getTransaction().begin();
            order.setEndDate(formatter.parse(formatter.format(new Date())));
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error returning book: " + ex.getMessage());
        }
    }
}
