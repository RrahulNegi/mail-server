package com.mailservice.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void mailSend(Email eParams) throws MessagingException {

		sendMail(eParams);

	}

	private void sendMail(Email eParams) throws MessagingException {
		boolean isHtml = true;
		MimeMessage message = mailSender.createMimeMessage();
		// MimeMessageHelper helper= new MimeMessageHelper(message, true,
		// "UTF-8");
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(eParams.getTo().toArray(new String[eParams.getTo().size()])[0]));
		message.setFrom(eParams.getFrom());
		message.setSubject(eParams.getSubject());
	
	
		String[] images = eParams.getImagePath().split(",");

		if (images.length > 0) {

			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(eParams.getMessage(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			ClassLoader classLoader = getClass().getClassLoader();

			for (int i = 0; i < images.length; i++) {
				 messageBodyPart = new MimeBodyPart();
				File file = new File(classLoader.getResource(images[i]).getFile());

				DataSource fds = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(fds));
				messageBodyPart.setHeader("Content-ID", "<"+images[i].split("/")[1]+">");
				multipart.addBodyPart(messageBodyPart);
			}
			// add image to the multipart
			
			// put everything together
			message.setContent(multipart);
		} else {
			message.setText(eParams.getMessage(), "text/html");
		}

		System.out
				.println("" + eParams.getTo() + "     " + eParams.getTo().toArray(new String[eParams.getTo().size()]));
		System.out.println(eParams.getFrom());
		System.out.println(eParams.getSubject());
		System.out.println(eParams.getMessage());
		mailSender.send(message);
	}
	
	

}
