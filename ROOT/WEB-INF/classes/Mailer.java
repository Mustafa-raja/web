


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
public class Mailer {
public static void send1(String to,String subject,String msg) throws MessagingException, ClassNotFoundException{
 System.out.println("just test...");
 Class.forName("javax.mail.Authenticator");
 System.out.println("Inside function");
final String user="through.test.mail@gmail.com";
final String pass="hrwjwhtzfgwipekn";

//1st step) Get the session object	
Properties props = new Properties();
props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.port", "587");
props.put("mail.smtp.starttls.enable", "true");

Session session = Session.getInstance(props,
 new javax.mail.Authenticator() {
  protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(user,pass);
   }
});
//2nd step)compose message
 System.out.println("above try");

 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 message.setSubject(subject);
 message.setText(msg);
 
 //3rd step)send message
 Transport.send(message);

 System.out.println("Done");
 System.out.println("above catch");

	
}
}
