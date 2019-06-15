package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Losowanie")
@RequestScoped
public class Losowanie {
    private String name;
    private int age;

    public String wyslij() {
        if(Math.random() < 0.2)
            return "OK";
        else
            return "NOT_OK";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
