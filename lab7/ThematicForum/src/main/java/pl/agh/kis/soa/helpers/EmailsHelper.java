package pl.agh.kis.soa.helpers;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailsHelper {
    public static void sendEmail(String emailAddress, String msg, String thematicList) throws MessagingException {
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
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(""));
        message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailAddress));
        message.setSubject("Notification from " + thematicList + " thematic list.");
        message.setText(msg);
        Transport.send(message);
        System.out.println(String.format("Email to %s has been sent.", emailAddress));
    }
}
