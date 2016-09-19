package uutils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailutils {
public static void sendMail(String to,String code) throws AddressException, MessagingException{
	
	Properties props = new Properties();
	props.setProperty("mail.smtp", "localhost");
	Session session=Session.getInstance(props, new Authenticator(){
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication("service@shop.com","111");
		
		
	}
		
	
});
	
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("service@shop.com"));
message.setRecipient(RecipientType.TO, new InternetAddress(to));
message.setSubject("来自爸爸的问候");
message.setContent("<h1>来自爸爸的邀请码</h1><h3><a herf='http://.....?code=+code+'>http://...../user_active.action?code=+code+</a></h3>", "text/html);charset=UTF-8");
Transport.send(message);	
}
}