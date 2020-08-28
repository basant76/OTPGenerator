import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class SendMail {
    public static void sendMail(String reciever, int otp) {
        // Recipient's email ID needs to be mentioned.
        String to = reciever;

        // Sender's email ID needs to be mentioned
        String from = "basantsoni7697@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("basantsoni7697@gmail.com", "Anup7898@#");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("OTP from Java App");

            // Now set the actual message
            message.setText("Your OTP is " + otp + "\nKindly do not share OTP with other.");
            //message.setContent("<h1>Hello</h1>","text/html;charset=utf-8");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println("Plz enter ur email");
        Scanner sc = new Scanner(System.in);
        String reciever = sc.nextLine();
        int OTP = OTPGenerator.generateNewOTP();
        sendMail(reciever, OTP);
        System.out.println("Plz enter OTP");
        int userOTP = sc.nextInt();
        if(userOTP == OTP)
            System.out.println("Welcome authenticated user");
        else
            System.out.println("Auth failed");

    }
}
