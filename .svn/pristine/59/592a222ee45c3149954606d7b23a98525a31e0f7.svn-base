package com.grouptwo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grouptwo.domain.Registration;
import com.grouptwo.repository.RegistrationDAO;


@Service
public interface RegistrationService {
	
	/**
	 * This is the method to connect up with the RegistrationDAO in the repository layer
	 *
	 */
	public void setRegistrationDAO(RegistrationDAO registrationDAO);

	/**
	 * This is the method to be used to list down all the records from the
	 * Registration table.
	 */
	public List<Registration> listRegistrations();
}
