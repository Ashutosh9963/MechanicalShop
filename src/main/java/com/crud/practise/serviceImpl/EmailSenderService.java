package com.crud.practise.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Value("${spring.mail.username}")
	@SuppressWarnings("unused")
	private String senderEmail;
	
	
	// Simple Mail Transfer Protocol is an Internet standard communication protocol for electronic mail transmission.
	public Boolean sendEmail(String toEmail, String subject, String body) {
		// Create an instance of SimplemailMessage
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ashutosh.at46@gmail.com");
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(body);
		Boolean status = false;
		try {
			javaMailSender.send(message);
			status = true;
		} catch (MailSendException e) {
			System.out.println(e);
		}
		if (status) {
			System.out.println("Mail sent successfully...");
			return status;
		}else {
			System.out.println("Error occured in sending mail...");
			return status;
		}
	}
}
