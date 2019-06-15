package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Author;
import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;
import pl.agh.kis.soa.model.Order;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class FiltersService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public List<String> genericFilter(String selectResult, Long authorId, Long bookId, Long clientId, Date startDate, Date endDate) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<String> result = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if(selectResult.equals("client")) {
            CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
            Root<Order> fromOrder = query.from(Order.class);
            Join<Order, Client> joinClient = fromOrder.join("client");
            Join<Order, Book> joinBook = fromOrder.join("book");
            Join<Book, Author> joinAuthor = joinBook.join("author");
            Predicate criteria = cb.conjunction();

            try {
                if (startDate != null)
                    criteria = cb.and(criteria, cb.greaterThanOrEqualTo(fromOrder.<Date>get("startDate"), formatter.parse(formatter.format(startDate))));
                if (endDate != null)
                    criteria = cb.and(criteria, cb.lessThanOrEqualTo(fromOrder.<Date>get("startDate"), formatter.parse(formatter.format(endDate))));
            }
            catch(Exception ex) {
                System.err.println("Error parsing date: " + ex.getMessage());
            }
            if (authorId != -1)
                criteria = cb.and(criteria, cb.equal(joinAuthor.<Long>get("id"), authorId));
            if (bookId != -1)
                criteria = cb.and(criteria, cb.equal(joinBook.<Long>get("id"), bookId));
            if (clientId != -1)
                criteria = cb.and(criteria, cb.equal(joinClient.<Long>get("id"), clientId));

            query.multiselect(joinClient.<String>get("name").alias("name"), joinClient.<String>get("surname").alias("surname"))
                    .where(criteria)
                    .distinct(true);
            TypedQuery<Tuple> tq = em.createQuery(query);
            for(Tuple r : tq.getResultList())
                result.add(r.get("name") + " " + r.get("surname"));
        } else {
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<Order> fromOrder = query.from(Order.class);
            Join<Order, Client> joinClient = fromOrder.join("client");
            Join<Order, Book> joinBook = fromOrder.join("book");
            Join<Book, Author> joinAuthor = joinBook.join("author");
            Predicate criteria = cb.conjunction();

            try {
                if (startDate != null)
                    criteria = cb.and(criteria, cb.greaterThanOrEqualTo(fromOrder.<Date>get("startDate"), formatter.parse(formatter.format(startDate))));
                if (endDate != null)
                    criteria = cb.and(criteria, cb.lessThanOrEqualTo(fromOrder.<Date>get("startDate"), formatter.parse(formatter.format(endDate))));
            }
            catch(Exception ex) {
                System.err.println("Error parsing date: " + ex.getMessage());
            }
            if (authorId != -1)
                criteria = cb.and(criteria, cb.equal(joinAuthor.<Long>get("id"), authorId));
            if (bookId != -1)
                criteria = cb.and(criteria, cb.equal(joinBook.<Long>get("id"), bookId));
            if (clientId != -1)
                criteria = cb.and(criteria, cb.equal(joinClient.<Long>get("id"), clientId));

            query.select(joinAuthor.get("name"))
                    .where(criteria).distinct(true);
            TypedQuery<String> tq = em.createQuery(query);
            result.addAll(tq.getResultList());
        }

        return result;
    }
}
