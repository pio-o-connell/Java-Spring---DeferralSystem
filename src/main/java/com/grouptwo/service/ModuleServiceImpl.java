package com.grouptwo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grouptwo.domain.Module;
import com.grouptwo.repository.ModuleDAO;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDAO moduleDAO;
	
	@Override
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	@Override
	public void createModule(String moduleId, int crnNumber, String name,
			String lectId, String semesterId) {
		moduleDAO.createModule(moduleId, crnNumber, name, lectId, semesterId);
	}

	@Override
	public void updateModuleName(String id, int crn, String name) {
		moduleDAO.updateModuleName(id, crn, name);
	}

	@Override
	public void deleteModule(String moduleId, int crnNumber) {
		moduleDAO.deleteModule(moduleId, crnNumber);

	}

	@Override
	public void createMultipleModules(List<Module> modules) {
		moduleDAO.createMultipleModules(modules);

	}

	@Override
	public Module getModule(String moduleId, int crnNumber) {
		return moduleDAO.getModule(moduleId, crnNumber);
	}

	@Override
	public Module getModuleByName(String name, int crnNumber) {
		return moduleDAO.getModuleByName(name, crnNumber);
	}

	@Override
	public List<Module> listStudentModules(String studId) {
		return moduleDAO.listStudentModules(studId);
	}

	@Override
	public List<Module> listModulesByLecturer(String lectId) {
		return moduleDAO.listModulesByLecturer(lectId);
	}

	@Override
	public List<Module> listModules() {
		return moduleDAO.listModules();
	}

	@Override
	public void registerStudentForModule(String studId, int crnNumber) {
		moduleDAO.registerStudentForModule(studId, crnNumber);

	}

	@Override
	public List<Module> listModulesByStudentId(String studId) {
		return moduleDAO.listModulesByStudentId(studId);
	}

	@Override
	public List<Module> listModulesById(String moduleId) {
		return moduleDAO.listModulesById(moduleId);
	}

}
