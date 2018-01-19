package com.grouptwo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grouptwo.domain.Registration;

@Repository
public interface RegistrationDAO {

	/**
	 * This is the method to be used to list down all the records from the
	 * Registration table.
	 */
	public List<Registration> listRegistrations();
	
	
	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();
}
