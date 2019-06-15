package pl.agh.kis.soa.model;

import java.util.ArrayList;
import java.util.List;

public class ThematicList {
    private String name;
    private List<Subscriber> subscribersList = new ArrayList<Subscriber>();

    public String getName() { return name; }
    public List<Subscriber> getSubscribersList() { return subscribersList; }

    public void setName(String name) { this.name = name; }
}
