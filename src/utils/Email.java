package utils;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import context.LoginContext;

public class Email {
	private static final Logger logger = LogManager.getLogger(LoginContext.class);
	
	/**
	 * sendEmail -  This method is used to send an email notification to any registered email.
	 * @param 		String sub
	 * @param 		String message
	 * @param 		String email
	 * @exception 	Exception
	 **/
	public static void sendEmail(String sub, String message, String email) {
		logger.info("Email::sendEmail() Begin");
		try {
			Properties cProperties = new Properties();
			cProperties.load(new FileInputStream("//resources/ceremonies"));
			logger.info("mail host is: "+cProperties.getProperty("emailHost"));
			Session mailConnection = Session.getInstance(cProperties);
				Message msg = new MimeMessage(mailConnection);
				Address from = new InternetAddress("ssarraf@csc.com");
				Address to = new InternetAddress(email);
				Address cc = new InternetAddress(cProperties.getProperty("emailCC"));
				msg.setFrom(from);
				msg.setRecipient(Message.RecipientType.TO, to);
				msg.setRecipient(Message.RecipientType.CC, cc);
				msg.setSubject("sub");
				msg.setContent("message","text/plain");
				Transport.send(msg);
				logger.info(" Email Has Been Sent Successfully");
			} 
		catch (Exception e) {
			logger.error("Email.sendMail(sub, message, email) Exception Encountered While Sending Email: "+ e);
		}
	}

}
