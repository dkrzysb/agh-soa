package pl.agh.kis.soa;


import org.json.JSONObject;
import pl.agh.kis.soa.model.Subscriber;
import pl.agh.kis.soa.model.ThematicList;
import pl.agh.kis.soa.service.SubscribersService;
import pl.agh.kis.soa.service.ThematicsListService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "MessagesManager")
@ApplicationScoped
public class MessagesManager {
    private String[] subscribersToSendEmail;
    private String message;
    private String selectedThematicList;
    private MessagesPublisher messagesPublisher = new MessagesPublisher();

    @ManagedProperty(value="#{ThematicsListManager}")
    private ThematicsListManager thematicsListManager;

    public String[] getSubscribersToSendEmail() { return subscribersToSendEmail; }
    public String getMessage() { return message; }
    public String getSelectedThematicList() { return selectedThematicList; }

    public void setSubscribersToSendEmail(String[] subscribersToSendEmail) { this.subscribersToSendEmail = subscribersToSendEmail; }
    public void setMessage(String message) { this.message = message; }
    public void setSelectedThematicList(String selectedThematicList) { this.selectedThematicList = selectedThematicList; }
    public void setThematicsListManager(ThematicsListManager thematicsListManager) { this.thematicsListManager = thematicsListManager; }

    public String sendMessageToAllSubscribers() {
        messagesPublisher.sendMessageToAllSubscribers(message, thematicsListManager.getSelectedThematicList());
        message = "";

        return "index";
    }

    public String sendMessageToSpecificSubscribers() {
        messagesPublisher.sendMessageToSpecificSubscribers(message, subscribersToSendEmail, thematicsListManager.getSelectedThematicList());
        message = "";

        return "index";
    }
}
