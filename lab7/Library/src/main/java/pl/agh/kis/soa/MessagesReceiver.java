package pl.agh.kis.soa;

import javax.ejb.ActivationConfigProperty;
import javax.mail.*;

import org.json.JSONObject;
import pl.agh.kis.soa.model.Client;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@MessageDriven(mappedName = "SOA_Test", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
                                  propertyValue = "SOA_Test")
})
public class MessagesReceiver implements MessageListener {
    @Inject
    private ClientsService clientsService;

    @Override
    public void onMessage(Message msg) {
        TextMessage txtMsg = null;
        try {
            if(msg instanceof TextMessage) {
                txtMsg = (TextMessage)msg;
                String txt = txtMsg.getText();
                JSONObject jobject = new JSONObject(txt);
                try {
                    String clientEmail = (String)jobject.get("clientEmail");
                    boolean notifications = (boolean)jobject.get("notifications");
                    String notificationType = (String)jobject.get("notificationType");
                    String bookTitle = (String)jobject.get("bookTitle");
                    String bookAuthor = (String)jobject.get("bookAuthor");
                    if(notifications)
                        sendEmail(clientEmail, notificationType, bookTitle, bookAuthor);
                }
                catch(NumberFormatException ex) {
                    System.err.println("Error parsing clientID: " + ex.getMessage());
                }
            }
        }
        catch(JMSException ex) {
            System.err.println("Error receiving message: " + ex.getMessage());
        }
    }

    private void sendEmail(String emailAddress, String notificationType, String bookTitle, String bookAuthor) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties,  new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "", "");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailAddress));
            if(notificationType.equals("newBookNotification")) {
                message.setSubject("New book in library is available ! Check it out !");
                message.setText(String.format("New book %s written by %s is available!", bookTitle, bookAuthor));
                System.out.println(String.format("Notification for user with email address %s: New book %s written by %s is available!", emailAddress, bookTitle, bookAuthor));
            }
            else {
                message.setSubject("Book is available again!");
                message.setText(String.format("Book %s written by %s is available again ! Order it now before someone overtakes you.", bookTitle, bookAuthor));
                System.out.println(String.format("Notification for user with email address %s: Book %s written by %s is available again ! Order it now before someone overtakes you.",
                        emailAddress, bookTitle, bookAuthor));
            }
            Transport.send(message);
            System.out.println(String.format("Email to %s has been sent.", emailAddress));
        }
        catch(MessagingException ex) {
            System.err.println(String.format("Error sending email to %s: %s", emailAddress, ex.getMessage()));
        }
    }
}
