package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Client;

import javax.annotation.Resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MessagesSender {
    @Resource(lookup = "java:/jms/queue/SOA_test")
    private Queue messagesQueue;
    @Inject
    private JMSContext context;

    public void sendMessage(String message) {
        try {
            context.createProducer().send(messagesQueue, message);
        }
        catch(Exception ex) {
            System.err.println("Error in sending message: " + ex.getMessage());
        }
    }
}
