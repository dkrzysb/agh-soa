package pl.agh.kis.soa.ejb3.server.war;

import pl.agh.kis.soa.ejb3.server.imp.TestAddBean;
import pl.agh.kis.soa.ejb3.server.imp.TestBeanCounter;


import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ApplicationScoped
@ManagedBean(name = "Manager")
public class Manager {
    private int firstNumber;
    private int secondNumber;
    private int calculatedSum;
    private long counter;

    @EJB
    TestAddBean testAddBean;
    @EJB
    TestBeanCounter testBeanCounter;

    public int getFirstNumber() { return firstNumber; }
    public int getSecondNumber() { return secondNumber; }
    public int getCalculatedSum() { return calculatedSum; }
    public long getCounter() { return counter; }

    public void setFirstNumber(int firstNumber) { this.firstNumber = firstNumber; }
    public void setSecondNumber(int secondNumber) { this.secondNumber = secondNumber; }

    public void sum() {
        calculatedSum = testAddBean.add(firstNumber, secondNumber);
    }

    public void increment() {
        testBeanCounter.increment();
        counter = testBeanCounter.getNumber();
    }
}
