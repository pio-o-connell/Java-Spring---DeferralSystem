package com.grouptwo.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.grouptwo.service.MailService;


@Component
public class Mail implements MailService {

	@Autowired
	private JavaMailSender mailSender;	
	
	public void sendProgrammeDeferralMail(String studentName, String studentId, String programmeId) { 
		
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setFrom("citdeferralsystem64@hotmail.com");
		message.setTo("citadmissions64@hotmail.com");
		message.setSubject("Request Programme Deferral");
		message.setText("A request for a programme deferral has been submitted.\n\n" + 
		"Student Name: " + studentName + "\nStudent ID: " + studentId +"\nProgramme ID: " + programmeId);
		mailSender.send(message);	
	}

	@Override
	public void sendModuleDeferralMail(String studentName, String studentId, String moduleId, int crnNumber) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setFrom("citdeferralsystem64@hotmail.com");
		message.setTo("citadmissions64@hotmail.com");
		message.setSubject("Request Module Deferral");
		message.setText("A request for a module deferral has been submitted.\n\n" + 
		"Student Name: " + studentName + "\nStudent ID: " + studentId +"\nModule ID: " + moduleId + "\nCRN: " + crnNumber );
		mailSender.send(message);
		
	}




}
