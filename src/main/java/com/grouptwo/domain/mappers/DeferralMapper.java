package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Deferral;

@SuppressWarnings("rawtypes")
public class DeferralMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Deferral deferral = new Deferral();
		deferral.setDefId(rs.getInt("Def_ID"));
		deferral.setStudentId(rs.getString("Student_ID"));
		deferral.setLectId(rs.getString("Lect_ID"));
		deferral.setProgrammeId(rs.getString("Programme_ID"));
		deferral.setModuleId(rs.getString("Module_ID"));
		deferral.setApproval(rs.getString("Approved"));
		return deferral;

	}

}