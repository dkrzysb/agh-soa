package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Client;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ClientsService {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public List<Client> getClients() {
        EntityManager em = factory.createEntityManager();

        try {
            Query query = em.createNamedQuery("get all clients");
            return query.getResultList();
        }
        catch(Exception ex) {
            System.err.println("Error in getting records: " + ex.getMessage());
            return null;
        }
    }
}
