package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Author;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class AuthorsService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public List<Author> getAllAuthors() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Author> query = em.createNamedQuery("get all authors", Author.class);

        return query.getResultList();
    }
}
