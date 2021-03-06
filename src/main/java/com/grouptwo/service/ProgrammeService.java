package com.grouptwo.service;

import java.util.List;

import com.grouptwo.domain.Programme;
import com.grouptwo.repository.ProgrammeDAO;

public interface ProgrammeService {
	
	/**
	 * This is the method to be used to connect up with the ProgrammeDAO
	 */
	public void setProgrammeDAO(ProgrammeDAO programmeDAO);

	/**
	 * This is the method to be used to create a record in the Programme table.
	 */
	public void createProgramme(String programmeId, int numYears,
			String coordinatorId, String startMonth);

	/**
	 * This is the method to be used to delete a record from the Programme table
	 * corresponding to a passed Programme ID.
	 */
	public  List<Programme> deleteProgramme(String programmeId);

	/**
	 * This is the method to be used to return a student's registered Programme
	 * ID.
	 */
	public String getStudentProgrammeId(String studId);

	/**
	 * This is the method to be used to list down all the records from the
	 * Programme table.
	 */
	public List<Programme> listProgrammes();
	
	/**
	 * This is the method to be used to show a specific record from the
	 * Programme table.
	 */
	public Programme getProgramme(String progId);
	
	/**
	 * This is the method to be used to update a coordinators id record for a 
	 * specific Programme.
	 */
	public Programme updateProgrammeCoordinator(String progId,String coordinatorId);
}
