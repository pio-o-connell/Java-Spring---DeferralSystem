package com.grouptwo.service;

import java.util.List;

import com.grouptwo.domain.Lecturer;
import com.grouptwo.repository.LecturerDAO;

public interface LecturerService {
	
	/**
	 * This is the method to be used to connect up with the LecturerDAO
	 */
	public void setLecturerDAO(LecturerDAO lecturerDAO);

	/**
	 * This is the method to be used to create a record in the Lecturer table.
	 */

	public void createLecturer(String lectId, String firstName,
			String lastName, String email);

	/**
	 * This is the method to be used to update a lecturer record.
	 */
	public void updateLecturer(String id, String firstname, String lastname,
			String email);
	
	/**
	 * This is the method to be used to update a lecturer record, last name only
	 */
	public void updateLecturer(String id, String lastname);

	/**
	 * This is the method to be used to delete a record from the Student table
	 * corresponding to a passed Student ID.
	 */
	/**
	 * This is the method to be used to delete a record from the Lecturer table.
	 */
	public void deleteLecturer(String lectId);

	/**
	 * This is the method to be used to create multiple lecturers.
	 */
	public void createMultipleLecturers(final List<Lecturer> lecturers);

	/**
	 * This is the method to be used to delete multiple lecturers.
	 */
	public void deleteMultipleLecturers(final List<Lecturer> lecturers);
	
	/**
	 * This is the method to be used to get a specific Lecturer.
	 */
	public Lecturer getLecturer(String id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Lecturer table.
	 */
	public List<Lecturer> listLecturers();
}
