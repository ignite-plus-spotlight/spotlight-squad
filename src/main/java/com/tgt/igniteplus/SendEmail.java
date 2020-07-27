package com.tgt.igniteplus;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) {

        final String username = "btsignite99@gmail.com";
        final String password = "yashu2020";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("btsignite@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("yashaswiniraju99@gmail.com")
            );
            message.setSubject("Received Award");
            Multipart emailContent = new MimeMultipart();

            //textbodypart
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Congratulations");

            //attachment body part
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("C:/Users/YASHASWINI/Downloads/award-template.pdf");

            //attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //attach multipart to message
            message.setContent(emailContent);

            Transport.send(message);
            System.out.println("Message sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
