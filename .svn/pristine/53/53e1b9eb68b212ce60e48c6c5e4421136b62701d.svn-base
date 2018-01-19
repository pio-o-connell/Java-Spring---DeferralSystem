package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Student;

@SuppressWarnings("rawtypes")
public class StudentMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Student student = new Student();
		student.setStudentId(rs.getString("Student_ID"));
		student.setFirstName(rs.getString("Firstname"));
		student.setLastName(rs.getString("Surname"));
		student.setEmail(rs.getString("student_Email"));
		return student;

	}

}