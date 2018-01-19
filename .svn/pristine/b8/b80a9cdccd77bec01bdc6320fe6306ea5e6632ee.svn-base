package com.grouptwo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.grouptwo.domain.Module;

@Repository
public interface ModuleDAO {

	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Module table.
	 */

	public void createModule(String moduleId, int crnNumber, String name,
			String lectId, String semesterId);

	/**
	 * This is the method to be used to update a record in the Module table.
	 */

	public void updateModuleName(String id, int crn, String name);

	/**
	 * This is the method to be used to delete a record from the Module table
	 * corresponding to a passed Module ID and CRN.
	 */
	public void deleteModule(String moduleId, int crnNumber);

	/**
	 * This is the method to be used to create multiple Modules.
	 */
	public void createMultipleModules(final List<Module> modules);

	/**
	 * This is the method to be used to list down a record from the Module table
	 * corresponding to a passed Module ID and CRN.
	 */
	public Module getModule(String moduleId, int crnNumber);
	
	/**
	 * This is the method to be used to list down a record from the Module table
	 * corresponding to a passed Module Name and CRN.
	 */
	public Module getModuleByName(String name, int crnNumber);

	/**
	 * This is the method to be used to list all the modules corresponding to a
	 * Student ID.
	 */
	public List<Module> listStudentModules(String studId);

	/**
	 * This is the method to be used to list all the modules corresponding to a
	 * Lecturer ID.
	 */
	public List<Module> listModulesByLecturer(String lectId);
	
	/**
	 * This is the method to be used to list all the modules corresponding to a
	 * Module ID.
	 */
	public List<Module> listModulesById(String moduleId);

	/**
	 * This is the method to be used to list down all the records from the
	 * Module table.
	 */
	public List<Module> listModules();

	/**
	 * This is the method to be used to register a student for a specfic module.
	 */
	public void registerStudentForModule(String studId, int crnNumber);

	/**
	 * This is the method used to count the rows in the table.
	 */
	public int countRows();
	
	/**
	 * This is the method to list modules a student is registered for
	 */
	public List<Module> listModulesByStudentId(String studId);
}
