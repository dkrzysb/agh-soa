package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@ApplicationScoped
public class AdsManager {
    private int clicks;

    public int getClicks() { return clicks; }

    public void addClick() { ++clicks; }
}
