package com.grouptwo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grouptwo.domain.Programme;
import com.grouptwo.repository.ProgrammeDAO;

@Service
public class ProgrammeServiceImpl implements ProgrammeService {

	@Autowired
	private ProgrammeDAO programmeDAO;
	
	public void setProgrammeDAO(ProgrammeDAO programmeDAO){
		this.programmeDAO = programmeDAO;
	}
	
	@Override
	public void createProgramme(String programmeId, int numYears,
			String coordinatorId, String startMonth) {
		programmeDAO.createProgramme(programmeId, numYears, coordinatorId, startMonth);
	}

	@Override
	public List<Programme> deleteProgramme(String programmeId) {
		return programmeDAO.deleteProgramme(programmeId);
	}

	@Override
	public String getStudentProgrammeId(String studId) {
		return programmeDAO.getStudentProgrammeId(studId);
	}

	@Override
	public List<Programme> listProgrammes() {
		return programmeDAO.listProgrammes();
	}

	@Override
	public Programme getProgramme(String progId) {
		return programmeDAO.getProgramme(progId);
	}

	@Override
	public Programme updateProgrammeCoordinator(String progId,
			String coordinatorId) {
		return programmeDAO.updateProgrammeCoordinator(progId, coordinatorId);
	}

}
