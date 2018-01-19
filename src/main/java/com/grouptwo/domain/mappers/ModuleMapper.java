package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Module;

@SuppressWarnings("rawtypes")
public class ModuleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Module module = new Module();
		module.setModuleId(rs.getString("Module_ID"));
		module.setCrnNumber(rs.getInt("CRN"));
		module.setName(rs.getString("Name"));
		module.setLectId(rs.getString("Lect_ID"));
		module.setSemesterId(rs.getString("Semester_ID"));
		return module;

	}

}