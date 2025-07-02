package com.example;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void main(String[] args) {
        final String to = "sandeshbobade8055@gmail.com";
        final String from = "karanwaghachoure95@gmail.com";  // Apna Gmail daalo yaha
        final String password = "jldarwszchovamzf";       // Yaha app password daalo (16 characters, bina spaces)

        String otp = generateOTP(6);
        String subject = "Your OTP Code";
        String body = "Your OTP is: " + otp;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Debug on for detailed logs
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("OTP sent successfully: " + otp);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String generateOTP(int length) {
        String numbers = "0123456789";
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(rand.nextInt(numbers.length())));
        }

        return otp.toString();
    }
}
