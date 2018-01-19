package com.grouptwo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.grouptwo.domain.Semester;
import com.grouptwo.domain.mappers.SemesterMapper;

@Repository
public class SemesterJdbcDaoSupport extends JdbcDaoSupport implements SemesterDAO {

	@Autowired
	SemesterJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 

	@Override
	public List<Semester> listSemesters() {
		String SQL = "select * from semester";
		List<Semester> semesterList = getJdbcTemplate().query(SQL, new SemesterMapper());
		return semesterList;
	}

}
