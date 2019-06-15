package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class BooksService {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("Books");

    public Book[] getBooks() {
        EntityManager em = factory.createEntityManager();

        try {
            Query query = em.createQuery("FROM Book", Book.class);
            List<Book> books = query.getResultList();
            return books.toArray(new Book[books.size()]);
        }
        catch(Exception ex) {
            System.err.println("Error in getting records: " + ex);
            return null;
        }
    }

    public Book getBookById(Long id) {
        EntityManager em = factory.createEntityManager();

        try {
            Book book = em.find(Book.class, id);
            return book;
        }
        catch(Exception ex) {
            System.err.println("Error in getting record: " + ex);
            return null;
        }
    }

    public void addBook(Book book) {
        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            System.err.println("Error in adding record: " + ex);
        }
    }

    public void removeBook(Long id) {
        EntityManager em = factory.createEntityManager();

        try {
            Book bookToRemove = em.find(Book.class, id);
            em.getTransaction().begin();
            em.remove(bookToRemove);
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            System.err.println("Error in removing record: " + ex);
        }
    }

    public void updateBook(Book book) {
        EntityManager em = factory.createEntityManager();

        try {
            Book bookToUpdate = em.find(Book.class, book.getId());
            em.getTransaction().begin();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setType(book.getType());
            bookToUpdate.setPrice(book.getPrice());
            bookToUpdate.setCurrency(book.getCurrency());
            bookToUpdate.setPages(book.getPages());
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            System.err.println("Error in updating record: " + ex);
        }
    }
}
