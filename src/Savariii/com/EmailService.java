package Savariii.com;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import jakarta.mail.PasswordAuthentication;

class EmailService {

    private int otp;

    public int generateOTP() {
        Random r = new Random();
        otp = 1000 + r.nextInt(9000);
        return otp;
    }

    public void sendOTP(String toEmail) {

        String fromEmail = "rohan.yewale252005@gmail.com";
        String password = "kthj qxfa xlia zaid";
        		

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
            );

            message.setSubject("OTP for Ola App");

            otp = generateOTP();

            message.setText("Your OTP is: " + otp);

            Transport.send(message);

            System.out.println("OTP sent successfully");

        } catch (Exception e) {
            System.out.println("Error sending email");
            e.printStackTrace();
        }
    }

    public boolean verifyOTP(int userOtp) {
        return otp == userOtp;
    }
}