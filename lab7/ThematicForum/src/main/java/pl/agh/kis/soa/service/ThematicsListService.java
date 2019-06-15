package pl.agh.kis.soa.service;

import pl.agh.kis.soa.model.ThematicList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ThematicsListService {
    private List<ThematicList> thematicsList;

    @PostConstruct
    public void init() {
        thematicsList = new ArrayList<ThematicList>();
    }

    public void addThematicList(ThematicList thematicList) {
        thematicsList.add(thematicList);
    }

    public List<ThematicList> getThematicsList() { return thematicsList; }
}
