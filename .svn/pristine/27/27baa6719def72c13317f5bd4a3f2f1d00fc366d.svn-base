package com.grouptwo.domain.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.grouptwo.domain.Programme;

@SuppressWarnings("rawtypes")
public class ProgrammeMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Programme programme = new Programme();
		programme.setProgrammeId(rs.getString("Programme_ID"));
		programme.setNumYears(rs.getInt("Num_Years"));
		programme.setCoordinatorId(rs.getString("Coord_ID"));
		programme.setProgYear(rs.getInt("Prog_Year"));
		return programme;

	}

}