package com.mailservice.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mailData")
public class MailBean {
	@Id
	private String id;
	private int mailId;
	private String mailSubject;
	private String mailBody;
	private String[]imagePath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMailId() {
		return mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String[] getImagePath() {
		return imagePath;
	}
	public void setImagePath(String[] imagePath) {
		this.imagePath = imagePath;
	}
	

}
