package pl.agh.kis.soa.model;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Subscriber implements MessageListener, ExceptionListener {
    private String name;
    private String surname;
    private String emailAddress;
    private String login;

    public Subscriber() {}
    public Subscriber(String name, String surname, String emailAddress, String login) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.login = login;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmailAddress() { return emailAddress; }
    public String getLogin() { return login; }

    public void subscribe(ThematicList thematicList) {
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("java:/ConnectionFactory");
            Destination topic = (Destination) ctx.lookup("java:/jms/topic/SOA_TestTopic");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer receiver = session.createConsumer(topic, "THEMATICLIST = '" + thematicList.getName() + "'");
            receiver.setMessageListener(this);
            connection.start();
        }
        catch(Exception ex) {
            System.err.println("Error creating subscription to topic: " + ex.getMessage());
        }

        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("java:/ConnectionFactory");
            Destination queue = (Destination) ctx.lookup("java:/jms/queue/SOA_TestQueue");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer receiver = session.createConsumer(queue,"SUBSCRIBERLOGIN = '" + login + "'");
            receiver.setMessageListener(this);
            connection.start();
        }
        catch(Exception ex) {
            System.err.println("Error creating subscription to queue: " + ex.getMessage());
        }
    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage)message;
        try {
            String txtMsg = msg.getText();
            System.out.println(String.format("Message to subscriber %s: %s", login, txtMsg));
        }
        catch(Exception ex) {
            System.err.println(String.format("Error sending email to subscriber %s: %s", login, ex.getMessage()));
        }
    }

    @Override
    public void onException(JMSException ex) {
        System.err.println(String.format("Error receiving message by subscriber %s: %s", login, ex.getMessage()));
    }
}
