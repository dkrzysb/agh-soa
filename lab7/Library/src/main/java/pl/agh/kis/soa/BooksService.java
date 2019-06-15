package pl.agh.kis.soa;

import org.json.JSONObject;
import pl.agh.kis.soa.model.Author;
import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class BooksService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();
    @Inject
    private MessagesSender messagesSender;

    public Book[] getBooks() {
        EntityManager em = factory.createEntityManager();

        try {
            Query query = em.createQuery("select b from Book b", Book.class);
            List<Book> books = query.getResultList();
            return books.toArray(new Book[books.size()]);
        }
        catch(Exception ex) {
            System.err.println("Error in getting records: " + ex.getMessage());
            return null;
        }
    }

    public Book getBookById(Long id) {
        EntityManager em = factory.createEntityManager();

        try {
            return em.find(Book.class, id);
        }
        catch(Exception ex) {
            System.err.println("Error in getting record: " + ex.getMessage());
            return null;
        }
    }

    public void addBook(Book book, Author author) {
        EntityManager em = factory.createEntityManager();

        try {
            TypedQuery<Author> query = em.createQuery("select a " + "from Author a " + "where a.name LIKE '" + author.getName() + "'", Author.class);
            if(!query.getResultList().isEmpty()) {
                author = query.getResultList().get(0);
            }
            em.getTransaction().begin();
            author.addBook(book);
            em.persist(author);
            em.getTransaction().commit();

            TypedQuery<Client> clientsQuery = em.createQuery("select c from Client c", Client.class);
            List<Client> clients = clientsQuery.getResultList();
            for(Client client : clients) {
                JSONObject jobject = new JSONObject();
                jobject.put("clientEmail", client.getEmailAddress());
                jobject.put("notifications", client.getNotifications());
                jobject.put("notificationType", "newBookNotification");
                jobject.put("bookTitle", book.getTitle());
                jobject.put("bookAuthor", book.getAuthor().getName());
                messagesSender.sendMessage(jobject.toString());
            }
        }
        catch(Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error in adding record: " + ex.getMessage());
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
            em.getTransaction().rollback();
            System.err.println("Error in removing record: " + ex.getMessage());
        }
    }

    public void updateBook(Book book) {
        EntityManager em = factory.createEntityManager();

        try {
            Book bookToUpdate = em.find(Book.class, book.getId());
            Author authorToUpdate = em.find(Author.class, book.getAuthor().getId());

            em.getTransaction().begin();
            bookToUpdate.setTitle(book.getTitle());
            authorToUpdate.setName(book.getAuthor().getName());
            bookToUpdate.setPages(book.getPages());
            em.getTransaction().commit();
        }
        catch(Exception ex) {
            em.getTransaction().rollback();
            System.err.println("Error in updating record: " + ex.getMessage());
        }
    }
}
