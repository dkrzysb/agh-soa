package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;
import pl.agh.kis.soa.model.Order;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class OrdersService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public String order(Book book, Order order, Long clientId) {
        EntityManager em = factory.createEntityManager();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String jqpl = "select o from Order o WHERE o.book.id = :id";
        Query query = em.createQuery(jqpl, Order.class)
                .setParameter("id", book.getId());
        if(query.getResultList().size() != 0) {
            Order dbOrder = (Order)query.getResultList().get(query.getResultList().size() - 1);
            Date now = new Date();
            Date endDate = null;
            try {
                now = formatter.parse(formatter.format(now));
                endDate = formatter.parse(formatter.format(dbOrder.getEndDate()));

                if (now.compareTo(endDate) < 0)
                    return "This book is already reserved. Predicted return date: " + formatter.parse(formatter.format(((Order)query.getResultList().stream().findFirst().get()).getEndDate()));
            }
            catch(Exception ex) {
                System.err.println("Error parsing date: " + ex.getMessage());
            }
        }

        try {
            order.setStartDate(formatter.parse(formatter.format(order.getStartDate())));
            order.setEndDate(formatter.parse(formatter.format(order.getEndDate())));
        }
        catch(Exception ex) {
            System.err.println("Error parsing date: " + ex.getMessage());
        }

        query = em.createQuery("select c from Client c where c.id = :id", Client.class)
                .setParameter("id", clientId);
        Client client = (Client)query.getSingleResult();

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
        Order order = (Order)query.getResultList().get(query.getResultList().size() - 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            em.getTransaction().begin();
            order.setEndDate(formatter.parse(formatter.format(new Date())));
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error parsing date: " + ex.getMessage());
        }
    }
}
