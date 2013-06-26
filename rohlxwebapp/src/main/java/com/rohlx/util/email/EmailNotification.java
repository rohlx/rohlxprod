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
	public static Properties prop = new Properties();

	public static boolean sendEmail(String aFromEmailAddr, String aToEmailAddr,
			String aSubject, String aBody) {
		
		boolean emailSent = true;

		try {

			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/config.properties"));

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("###"+e.getMessage());
			throw new RuntimeException();

		}
		Session session = Session.getDefaultInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(prop
								.getProperty("username"), prop
								.getProperty("password"));
					}
				});
		MimeMessage message = new MimeMessage(session);
		try {
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					aToEmailAddr));
			message.setSubject(aSubject);
			message.setText(aBody);
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			System.err.println("Cannot send email. " + ex);
		}
		return emailSent;
	}

}
