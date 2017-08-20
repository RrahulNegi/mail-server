package com.mailservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailservice.bean.MailBean;
import com.mailservice.bean.MailDataBean;
import com.mailservice.dao.MailDao;
import com.mailservice.mail.Email;
import com.mailservice.mail.EmailService;

@Service("mailService")

public class MailServiceImpl implements MailService {

	@Autowired
	MailDao mailDao;
	
	@Autowired
	EmailService emailService;
	
	
	
	 
	 
	public  void sendMail(MailDataBean mail) {
		 
		 
		 		String from = mail.getFrom() ;
		 		String to = mail.getTo();
		 		String subject = mail.getSubject();
		 		String mailBody=mail.getMailBody();
		 		String imagePath=mail.getImagePath();
		 		
		 		System.out.println("Frm :"+from+" to :"+to+" subject : "+subject+" body :"+mailBody);
		 		
		 
		 	Email email = new Email(from, to, subject, mailBody, imagePath);
		 		email.setHtml(true);
		 		try {
					emailService.mailSend(email);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}

	
}
