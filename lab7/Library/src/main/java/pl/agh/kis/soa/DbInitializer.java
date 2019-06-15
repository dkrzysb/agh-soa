package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Author;
import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class DbInitializer {
    private static DbInitializer _instance = null;
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("Library");
    private DbInitializer() { }

    public void Seed() {
        EntityManager em = factory.createEntityManager();

        seedAuthorsWithBooks(em);
        seedClients(em);
    }

    public static DbInitializer getInstance() {
        if(_instance == null)
            _instance = new DbInitializer();
        return _instance;
    }

    public EntityManagerFactory getEntityManagerFactory() { return factory; }

    private void seedAuthorsWithBooks(EntityManager em) {
        Author author1 = new Author("Henryk Sienkiewicz");
        author1.addBook(new Book("Quo Vadis", 460));
        author1.addBook(new Book("Latarnik", 24));
        author1.addBook(new Book("W pustyni i w puszczy", 296));
        Author author2 = new Author("George Orwell");
        author2.addBook(new Book("Folwark zwierzecy",136));
        Author author3 = new Author("Juliusz Słowacki");
        author3.addBook(new Book("Balladyna",170));
        author3.addBook(new Book("Kordian", 128));
        Author author4 = new Author("Adam Mickiewicz");
        author4.addBook(new Book("Pan Tadeusz",334));
        author4.addBook(new Book("Dziady",290));

        em.getTransaction().begin();
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);
        em.persist(author4);
        em.getTransaction().commit();
    }

    private void seedClients(EntityManager em) {
        Client client1 = new Client("Robert", "Lewandowski", "t0biashq@gmail.com", true);
        Client client2 = new Client("Janusz", "Tracz", "jtracz@gmail.com", false);
        Client client3 = new Client("Jadwiga", "Kaczka", "jkaczkak@gmail.com", false);
        Client client4 = new Client("Hanna", "Moskal", "msk@gmail.com", false);

        em.getTransaction().begin();
        em.persist(client1);
        em.persist(client2);
        em.persist(client3);
        em.persist(client4);
        em.getTransaction().commit();
    }
}
