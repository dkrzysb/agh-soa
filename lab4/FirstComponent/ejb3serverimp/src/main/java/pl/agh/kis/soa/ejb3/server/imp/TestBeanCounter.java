package pl.agh.kis.soa.ejb3.server.imp;

import pl.agh.kis.soa.ejb3.server.api.ILocalTestBeanCounter;
import pl.agh.kis.soa.ejb3.server.api.IRemoteTestBeanCounter;
import pl.agh.kis.soa.ejb3.server.api.ITestBeanCounter;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;

@Singleton
@Remote(IRemoteTestBeanCounter.class)
@LocalBean
public class TestBeanCounter implements ITestBeanCounter {
    long counterNumber = 0;

    public void increment() {
        ++counterNumber;
    }

    public long getNumber() {
        return counterNumber;
    }
}
