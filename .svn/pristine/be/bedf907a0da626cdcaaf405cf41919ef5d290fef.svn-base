package com.grouptwo.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.grouptwo.domain.Programme;
import com.grouptwo.domain.mappers.ProgrammeMapper;
import com.grouptwo.repository.ProgrammeDAO;
@Repository
public class ProgrammeJdbcDaoSupport extends JdbcDaoSupport implements ProgrammeDAO {

	@Autowired
	ProgrammeJdbcDaoSupport(DataSource dataSource) {
		   setDataSource(dataSource);
	} 

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createProgramme(String programmeId, int numYears,String coordinatorId,String startMonth) {
		
		for (int x=0;x<numYears; x++){
		int progYear=x+1;
		//We want the Programme ID as format "****_Y*"
		String programmeIdFormatted=programmeId + "_Y" + progYear;
		
		//We need to define the semesters for the given Programme"
		String semesterId1=programmeIdFormatted + "_S1";
		String semesterId2=programmeIdFormatted + "_S2";
		
		//Create Programme 
		String SQL = "insert into programme (Programme_ID, Num_Years, Coord_ID, Prog_Year) values (?, ?, ?, ?)";
		getJdbcTemplate().update(SQL,new Object[] { programmeIdFormatted, numYears, coordinatorId, progYear  });
		
		//Need to create entries in Semester Table
		String SQL2 = "insert into semester (Semester_ID, Programme_ID, StartDate, FinishDate) values (?, ?, ?, ?)";
		if (startMonth=="Sept"){
		getJdbcTemplate().update(SQL2,new Object[] { semesterId1, programmeIdFormatted,  "Sept", "Dec"  });
		getJdbcTemplate().update(SQL2,new Object[] { semesterId2, programmeIdFormatted, "Jan", "May"  });
		
		}
		else if(startMonth=="Jan"){
			getJdbcTemplate().update(SQL2,new Object[] { semesterId1, programmeIdFormatted, "Jan", "May"  });
		getJdbcTemplate().update(SQL2,new Object[] { semesterId2, programmeIdFormatted, "Sept", "Dec"  });
		
		}
		
		}
			
	}
		
	
		
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public  List<Programme> deleteProgramme(String programmeId) {
		String SQL = "delete from programme where Programme_ID like ?";
		String allProgrammeYears=programmeId +"%";
		if(!programmeId.equals(""))//bug fix for blank entry which deletes all programmes from database
		getJdbcTemplate().update(SQL, new Object[] { allProgrammeYears});
		List<Programme> programmeList=listProgrammes();
		return programmeList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String getStudentProgrammeId(String studId) {
		String SQL = "select DISTINCT Programme_ID from programme"
			+ " WHERE Programme_ID in (SELECT Programme_ID FROM registration WHERE Student_ID = ?)";
		String programmeId = getJdbcTemplate().queryForObject(SQL,new Object[]{studId}, String.class);
		return programmeId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<Programme> listProgrammes() {
		String SQL = "select * from programme";
		List<Programme> programmeList = getJdbcTemplate().query(SQL, 
						new ProgrammeMapper());
		return programmeList;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(*) from programme";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	@Override
	public Programme getProgramme(String progId) {
		String SQL = "SELECT Programme_ID, Num_Years, Coord_ID, Prog_Year FROM programme WHERE Programme_ID = ? ";
		Programme studentsProg =(Programme) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{progId},new ProgrammeMapper());
		return studentsProg;
	}

	@Override
	public Programme updateProgrammeCoordinator(String coordinatorId,
			String progId ) {
		String SQL ="UPDATE programme SET Coord_ID = ? WHERE Programme_ID = ?";
		getJdbcTemplate().update(SQL,coordinatorId, progId);
		Programme modifiedProg = getProgramme(progId);
		return modifiedProg;
	}

	

	
}
