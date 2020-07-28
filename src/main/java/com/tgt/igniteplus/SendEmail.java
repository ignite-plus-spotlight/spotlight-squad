package com.tgt.igniteplus;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;



public class SendEmail {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the username");
        final String username = sc.nextLine();
        System.out.println("enter the password");
        final String password = sc.nextLine();
        System.out.println("enter the fromEmail");
        String fromEmail=sc.nextLine();
        System.out.println("enter the toEmail");
        String toEmail=sc.nextLine();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));

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
            System.out.println("Message sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
