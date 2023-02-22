package com.crud.practise.serviceImpl;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailWithAttacmentService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	public void sendEmailWithAttacment(String toEmail, String subject, String body, String attachment) {
		
		// create an instance of Mimemessage 
		MimeMessage message = mailSender.createMimeMessage();
		// create an instance of Mi
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
			mimeMessageHelper.setFrom("ashutosh.at46@gmail.com");
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body, true);
			// to send attachment create an instance of FileSystemResource
			
			FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			mailSender.send(message);
			System.out.println("Mail with attachment send successfully");
		} catch (MessagingException e) {
			e.getMessage();
		}
	}
	
	public void sendEmailWithTemplate(String toEmail, String subject, String body, String attachment) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("ashutosh.at46@gmail.com");
		javaMailSenderImpl.setPassword("isfmoawmvkphtodq");
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom("ashutosh.at46@gmail.com");
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setSubject(subject);
			
//			Boolean html = true;
			mimeMessageHelper.setText(body, true);
			
			FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
			mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
