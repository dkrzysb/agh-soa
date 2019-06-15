package pl.agh.kis.soa;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MessagesPublisher {
    public void sendMessageToAllSubscribers(String message, String thematicList) {
        try {
            InitialContext ctx = new InitialContext();
            Destination topic = (Destination)ctx.lookup("java:/jms/topic/SOA_TestTopic");
            ConnectionFactory connectionFactory = (ConnectionFactory)ctx.lookup("java:/ConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer sender = session.createProducer(topic);

            TextMessage msg = session.createTextMessage();
            msg.setText(thematicList + ": " + message);
            msg.setObjectProperty("THEMATICLIST", thematicList);
            sender.send(msg);
        }
        catch(Exception ex) {
            System.err.println("Error sending message: " + ex.getMessage());
        }
    }

    public void sendMessageToSpecificSubscribers(String message, String[] subscribersLogin, String thematicList) {
        try {
            Context ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("java:/ConnectionFactory");
            Destination queue = (Destination) ctx.lookup("java:/jms/queue/SOA_TestQueue");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer sender = session.createProducer(queue);

            TextMessage msg;
            for (String subscriberLogin : subscribersLogin) {
                msg = session.createTextMessage();
                msg.setObjectProperty("SUBSCRIBERLOGIN", subscriberLogin);
                msg.setText(thematicList + ":  " + message);
                sender.send(msg);
            }
        }
        catch(Exception ex) {
            System.err.println("Error sending message to specific subscribers: " + ex.getMessage());
        }
    }
}
