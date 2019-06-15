package pl.agh.kis.soa;


import pl.agh.kis.soa.model.Subscriber;
import pl.agh.kis.soa.model.ThematicList;
import pl.agh.kis.soa.service.SubscribersService;
import pl.agh.kis.soa.service.ThematicsListService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;
import java.util.Optional;

@ManagedBean(name = "ThematicsListManager")
@ApplicationScoped
public class ThematicsListManager {
    private String selectedThematicList;
    private ThematicList newThematicList = new ThematicList();

    @ManagedProperty(value="#{thematicsListService}")
    private ThematicsListService thematicsListService;

    public String getSelectedThematicList() { return selectedThematicList; }
    public ThematicList getNewThematicList() { return newThematicList; }
    public List<ThematicList> getThematicsList() { return thematicsListService.getThematicsList(); }
    public List<Subscriber> getSubscribersList() {
        Optional<ThematicList> thematicList = thematicsListService.getThematicsList().stream()
                .filter(tl -> tl.getName().equals(selectedThematicList))
                .findFirst();
        if(thematicList.isPresent())
            return thematicList.get().getSubscribersList();
        return null;
    }

    public void setSelectedThematicList(String selectedThematicList) { this.selectedThematicList = selectedThematicList; }
    public void setNewThematicList(ThematicList newThematicList) { this.newThematicList = newThematicList; }
    public void setThematicsListService(ThematicsListService thematicsListService) { this.thematicsListService = thematicsListService; }

    public String addThematicsList() {
        thematicsListService.addThematicList(newThematicList);
        newThematicList = new ThematicList();

        return "index";
    }
}
