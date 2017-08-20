package com.mailservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mailservice.bean.MailBean;
import com.mailservice.bean.MailDataBean;
import com.mailservice.mail.EmailService;
import com.mailservice.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/sendMail", method=RequestMethod.POST)
	@ResponseBody
	public String sendMail(@RequestBody MailDataBean mailDataBean){
		
		mailService.sendMail(mailDataBean);

		return "";
	}

}
