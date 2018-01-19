package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Lecturer;

@SuppressWarnings("rawtypes")
public class LecturerMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Lecturer lecturer = new Lecturer();
		lecturer.setLectId(rs.getString("Lect_ID"));
		lecturer.setFirstName(rs.getString("Firstname"));
		lecturer.setLastName(rs.getString("Surname"));
		lecturer.setEmail(rs.getString("Lecturer_Email"));
		return lecturer;

	}

}
