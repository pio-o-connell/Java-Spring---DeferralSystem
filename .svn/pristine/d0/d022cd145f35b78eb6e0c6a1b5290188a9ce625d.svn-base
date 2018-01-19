package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Registration;

@SuppressWarnings("rawtypes")
public class RegistrationMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Registration registration = new Registration();
		registration.setStudentId(rs.getString("Student_ID"));
		registration.setCrnNumber(rs.getInt("CRN"));
		registration.setProgrammeId(rs.getString("Programme_ID"));
		return registration;

	}

}