package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Semester;

@SuppressWarnings("rawtypes")
public class SemesterMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Semester semester = new Semester();
		semester.setSemesterId(rs.getString("Semester_ID"));
		semester.setProgrammeId(rs.getString("Programme_ID"));
		semester.setStartMonth(rs.getString("StartDate"));
		semester.setEndMonth(rs.getString("FinishDate"));
		return semester;

	}

}