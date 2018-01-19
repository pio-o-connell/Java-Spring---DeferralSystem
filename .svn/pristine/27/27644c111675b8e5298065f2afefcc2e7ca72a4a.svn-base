package com.grouptwo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.grouptwo.domain.Programme;
import com.grouptwo.repository.ProgrammeJdbcDaoSupport;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class ProgrammeJdbcDaoSupportTest {
	@Autowired
	ApplicationContext autoWireContext;
	ProgrammeJdbcDaoSupport programmeJdbcDaoSupport;
	ProgrammeJdbcDaoSupport semesterDaoSupport;

	//Test for Create Programme method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateProgramme() {
		
		//Confirm table row count
		programmeJdbcDaoSupport = (ProgrammeJdbcDaoSupport) autoWireContext.getBean("programmeJdbcDaoSupport");
		programmeJdbcDaoSupport.createProgramme("BSCM", 4, "L003","Jan");
		int rowCount = programmeJdbcDaoSupport.countRows();
		assertEquals(6, rowCount);
	}

	//Test for Delete Programme method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteProgramme() {
		
		//Confirm table row count
		programmeJdbcDaoSupport = (ProgrammeJdbcDaoSupport) autoWireContext.getBean("programmeJdbcDaoSupport");
		programmeJdbcDaoSupport.deleteProgramme("KCMSD");
		int rowCount = programmeJdbcDaoSupport.countRows();
		assertEquals(1, rowCount);
	}
	
	//Test for retrieving Student's Registered Programme ID
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)	
	public void testGetStudentProgrammeId(){
		
		//Confirm Programme ID
		programmeJdbcDaoSupport = (ProgrammeJdbcDaoSupport) autoWireContext.getBean("programmeJdbcDaoSupport");
		String programmeId=programmeJdbcDaoSupport.getStudentProgrammeId("R0020934");
		assertEquals(programmeId,"KCLDC_Y4");
		
	}
	
	//Test for retrieving list of Programmes
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListProgrammes(){
		programmeJdbcDaoSupport = (ProgrammeJdbcDaoSupport) autoWireContext.getBean("programmeJdbcDaoSupport");
		List<Programme> programmes=programmeJdbcDaoSupport.listProgrammes();
		assertEquals(programmes.size(), 2);	
		assertEquals(programmes.get(0).getProgrammeId(), "KCLDC_Y4");
		assertEquals(programmes.get(1).getProgrammeId(), "KCMSD_Y5");
			
	}
}
