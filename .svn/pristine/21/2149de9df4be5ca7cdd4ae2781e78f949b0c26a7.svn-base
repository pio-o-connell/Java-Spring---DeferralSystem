package com.grouptwo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.grouptwo.domain.Semester;

@Repository
public interface SemesterDAO {

	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);
	
	/**
	 * This is the method to be used to list down all the records from the
	 * Semester table.
	 */
	public List<Semester> listSemesters();
}
