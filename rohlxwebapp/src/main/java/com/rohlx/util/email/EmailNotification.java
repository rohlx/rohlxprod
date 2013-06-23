package com.rohlx.util.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotification {
	final static String username = "project@rohlx.com";
	final static String password = "rohlx1234";
	public static Properties prop = new Properties();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", "true");

		sendEmail("project@rohlx.com", "mgmuhilan@gmail.com", "test",
				"This is a test mail guys");
	}

	public static void sendEmail(String aFromEmailAddr, String aToEmailAddr,
			String aSubject, String aBody) {
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", "true");
		// Here, no Authenticator argument is used (it is null).
		// Authenticators are used to prompt the user for user
		// name and password.
		 Session session = Session.getDefaultInstance(prop,
		          new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, password);
		          }
		        });
		MimeMessage message = new MimeMessage(session);
		try {
			// the "from" address may be set in code, or set in the
			// config file under "mail.from" ; here, the latter style is used
			// message.setFrom( new InternetAddress(aFromEmailAddr) );
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					aToEmailAddr));
			message.setSubject(aSubject);
			message.setText(aBody);
			Transport.send(message);
		} catch (MessagingException ex) {
			System.err.println("Cannot send email. " + ex);
		}
	}

}
