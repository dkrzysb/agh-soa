package pl.agh.kis.soa.ejb3.server.imp;

import pl.agh.kis.soa.ejb3.server.api.ILocalTestAddBean;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class TestAddBean implements ILocalTestAddBean {
    public TestAddBean() {

    }

    public int add(int a, int b) {
        return a + b;
    }
}
