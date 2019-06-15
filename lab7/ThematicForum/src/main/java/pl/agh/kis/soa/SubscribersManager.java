package pl.agh.kis.soa;


import pl.agh.kis.soa.model.Subscriber;
import pl.agh.kis.soa.model.ThematicList;
import pl.agh.kis.soa.service.SubscribersService;
import pl.agh.kis.soa.service.ThematicsListService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean(name = "SubscribersManager")
@ApplicationScoped
public class SubscribersManager {
    private String selectedSubscriber;
    private String selectedThematicList;

    @ManagedProperty(value="#{subscribersService}")
    private SubscribersService subscribersService;
    @ManagedProperty(value="#{thematicsListService}")
    private ThematicsListService thematicsListService;

    public String getSelectedSubscriber() { return selectedSubscriber; }
    public String getSelectedThematicList() { return selectedThematicList; }
    public List<Subscriber> getSubscribers() { return subscribersService.getSubscribers(); }

    public void setSelectedSubscriber(String selectedSubscriber) { this.selectedSubscriber = selectedSubscriber; }
    public void setSelectedThematicList(String selectedThematicList) { this.selectedThematicList = selectedThematicList; }
    public void setSubscribersService(SubscribersService subscribersService) { this.subscribersService = subscribersService; }
    public void setThematicsListService(ThematicsListService thematicsListService) { this.thematicsListService = thematicsListService; }

    public String subscribe() {
        Subscriber subscriber = subscribersService.getSubscribers().stream()
                .filter(s -> s.getLogin().equals(selectedSubscriber))
                .findFirst()
                .get();
        ThematicList thematicList = thematicsListService.getThematicsList().stream()
                .filter(tl -> tl.getName().equals(selectedThematicList))
                .findFirst()
                .get();
        if(thematicList.getSubscribersList().stream().noneMatch(s -> s.getLogin().equals(subscriber.getLogin()))) {
            thematicList.getSubscribersList().add(subscriber);
            subscriber.subscribe(thematicList);
        }

        return "index";
    }
}
