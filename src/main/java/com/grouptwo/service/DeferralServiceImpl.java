package com.grouptwo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grouptwo.domain.Deferral;
import com.grouptwo.repository.DeferralDAO;

@Service
public class DeferralServiceImpl implements DeferralService {

	@Autowired
	private DeferralDAO deferralDAO;
	
	@Override
	public void setDeferralDAO(DeferralDAO deferralDAO) {
		this.deferralDAO = deferralDAO;

	}

	@Override
	public int createDeferralGetId(String studentID, String lectID,
			String progID, String moduleID) {
		return deferralDAO.createDeferralGetId(studentID, lectID, progID, moduleID);
	}

	@Override
	public void createModuleDeferral(String studentId, String moduleId,
			int crnNumber) {
		deferralDAO.createModuleDeferral(studentId, moduleId, crnNumber);

	}

	@Override
	public void createProgrammeDeferral(String studentId, String programmeId) {
		deferralDAO.createProgrammeDeferral(studentId, programmeId);
	}

	@Override
	public void deleteDeferralById(int id) {
		deferralDAO.deleteDeferralById(id);

	}

	@Override
	public void deleteDeferralsByStudentID(String studentId) {
		deferralDAO.deleteDeferralsByStudentID(studentId);

	}

	@Override
	public void deleteDeferralsByModuleID(String moduleId) {
		deferralDAO.deleteDeferralsByModuleID(moduleId);
	}

	@Override
	public Deferral getDeferral(int id) {
		return deferralDAO.getDeferral(id);
	}

	@Override
	public List<Deferral> listDeferrals() {
		return deferralDAO.listDeferrals();
	}

	@Override
	public List<Deferral> listDeferralsByStudent(String studentID) {
		return deferralDAO.listDeferralsByStudent(studentID);
	}

	@Override
	public List<Deferral> listDeferralsByProgramme(String programmeID) {
		return deferralDAO.listDeferralsByProgramme(programmeID);
	}

	@Override
	public List<Deferral> listUnapprovedDeferrals(String unapproved) {
		return deferralDAO.listUnapprovedDeferrals(unapproved);
	}

	@Override
	public List<Deferral> listUnapprovedDeferralsByProgramme(String unapproved,
			String programmeID) {
		return deferralDAO.listUnapprovedDeferralsByProgramme(unapproved, programmeID);
	}

	@Override
	public List<Deferral> listUnapprovedDeferralsByProgrammeCoordinatorName(
			String unapproved, String firstname) {
		return deferralDAO.listUnapprovedDeferralsByProgrammeCoordinatorName(unapproved, firstname);
	}

	@Override
	public List<Deferral> listApprovedDeferralsByStudent(String approved,
			String studentID) {
		return deferralDAO.listApprovedDeferralsByStudent(approved, studentID);
	}

	@Override
	public void approveModuleDeferral(String studId, String moduleId) {
		deferralDAO.approveModuleDeferral(studId, moduleId);

	}

	@Override
	public void approveProgrammeDeferral(String studId, String programmeId) {
		deferralDAO.approveProgrammeDeferral(studId, programmeId);

	}

	@Override
	public void updateDeferral(int id, String approval) {
		deferralDAO.updateDeferral(id, approval);

	}

	@Override
	public int createModuleDeferralGetId(String studentId, String moduleId,
			int crnNumber) {
		return deferralDAO.createModuleDeferralGetId(studentId, moduleId, crnNumber);
	}

	@Override
	public ArrayList<Integer> createProgrammeDeferralGetId(String studentId,
			String programmeId) {
		return deferralDAO.createProgrammeDeferralGetId(studentId, programmeId);
	}

	@Override
	public List<Deferral> listDeferralsByModule(String moduleID) {
		
		return deferralDAO.listDeferralsByModule(moduleID);
	}

	@Override
	public List<Deferral> listDeferralsByCoordinatorId(String coordinatorId) {
		return deferralDAO.listDeferralsByCoordinatorId(coordinatorId);
	}

	@Override
	public List<Deferral> listUnapprovedDeferralsByCoordinatorId(
			String coordinatorId) {
		return deferralDAO.listUnapprovedDeferralsByCoordinatorId(coordinatorId);
	}

}
