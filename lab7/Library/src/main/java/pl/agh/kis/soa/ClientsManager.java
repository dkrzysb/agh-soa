package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Book;
import pl.agh.kis.soa.model.Client;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ClientsManager")
@ApplicationScoped
public class ClientsManager {
    @ManagedProperty(value="#{clientsService}")
    private ClientsService clientsService;

    public List<Client> getClients() {
        List<Client> clients = clientsService.getClients();
        if(clients == null)
            return new ArrayList<>();
        return clients;
    }

    public void setClientsService(ClientsService clientsService) { this.clientsService = clientsService; }
}
