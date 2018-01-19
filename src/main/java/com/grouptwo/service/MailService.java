package com.grouptwo.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
	public void sendProgrammeDeferralMail(String studentName, String studentId, String programmeId);
	public void sendModuleDeferralMail(String studentName,String studentId,String moduleId, int crnNumber);
	
}
