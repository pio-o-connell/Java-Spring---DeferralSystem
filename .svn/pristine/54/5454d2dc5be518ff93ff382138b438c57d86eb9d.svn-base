package com.grouptwo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grouptwo.domain.Lecturer;
import com.grouptwo.repository.LecturerDAO;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Autowired
	private LecturerDAO lecturerDAO;
	
	@Override
	public void setLecturerDAO(LecturerDAO lecturerDAO) {
		this.lecturerDAO = lecturerDAO;
	}

	@Override
	public void createLecturer(String lectId, String firstName,
			String lastName, String email) {
		lecturerDAO.createLecturer(lectId, firstName, lastName, email);
	}

	@Override
	public void updateLecturer(String id, String firstname, String lastname,
			String email) {
		lecturerDAO.updateLecturer(id, firstname, lastname, email);
	}

	@Override
	public void updateLecturer(String id, String lastname) {
		lecturerDAO.updateLecturer(id, lastname);
	}

	@Override
	public void deleteLecturer(String lectId) {
		lecturerDAO.deleteLecturer(lectId);

	}

	@Override
	public void createMultipleLecturers(List<Lecturer> lecturers) {
		lecturerDAO.createMultipleLecturers(lecturers);
	}

	@Override
	public void deleteMultipleLecturers(List<Lecturer> lecturers) {
		lecturerDAO.deleteMultipleLecturers(lecturers);

	}

	@Override
	public Lecturer getLecturer(String id) {
		return lecturerDAO.getLecturer(id);
	}

	@Override
	public List<Lecturer> listLecturers() {
		return lecturerDAO.listLecturers();
	}

}
