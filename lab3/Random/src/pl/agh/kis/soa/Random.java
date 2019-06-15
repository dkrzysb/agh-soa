package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean(name = "Random", eager = true)
@ApplicationScoped
public class Random {
    private static int randomNumber = (int)(Math.random() * 5 + 1);
    private int firstPageCounter = 0;
    private int secondPageCounter = 0;
    private int thirdPageCounter = 0;
    private int fourthPageCounter = 0;
    private int fifthPageCounter = 0;
    private int hitPageCounter = 0;

    public int getFirstPageCounter() {
        return firstPageCounter;
    }

    public int getSecondPageCounter() {
        return secondPageCounter;
    }

    public int getThirdPageCounter() {
        return thirdPageCounter;
    }

    public int getFourthPageCounter() {
        return fourthPageCounter;
    }

    public int getFifthPageCounter() {
        return fifthPageCounter;
    }

    public int getHitPageCounter() {
        return hitPageCounter;
    }

    public String getPage() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int page = Integer.parseInt(params.get("page"));

        if (page == randomNumber) {
            ++hitPageCounter;
            randomNumber = (int)(Math.random() * 5 + 1);
            return "hit";
        }

        switch(page) {
            case 1:
                ++firstPageCounter;
                return "1";
            case 2:
                ++secondPageCounter;
                return "2";
            case 3:
                ++thirdPageCounter;
                return "3";
            case 4:
                ++fourthPageCounter;
                return "4";
            default:
                ++fifthPageCounter;
                return "5";
        }
    }
}
