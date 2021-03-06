package com.grouptwo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.grouptwo.domain.Lecturer;

@Repository
public interface LecturerDAO {

	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

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

	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();
}
