package pl.agh.kis.soa.service;

import pl.agh.kis.soa.model.Subscriber;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class SubscribersService {
    private List<Subscriber> subscribers;

    public SubscribersService() {
        subscribers = new ArrayList<Subscriber>();
        subscribers.add(new Subscriber("Robert", "Lewandowski", "littlealien5k@gmail.com", "rlewandowski"));
        subscribers.add(new Subscriber("Andrzej", "Gołota", "t0biashq@gmail.com", "agolota"));
    }

    public List<Subscriber> getSubscribers() { return subscribers; }
}
