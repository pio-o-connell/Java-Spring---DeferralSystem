package com.grouptwo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.grouptwo.domain.Lecturer;
import com.grouptwo.repository.LecturerJdbcDaoSupport;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class LecturerJdbcDaoSupportTest {
	@Autowired
	ApplicationContext autoWireContext;
	LecturerJdbcDaoSupport lecturerJdbcDaoSupport;
	

	//Test for Create Lecturer method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateLecturer() {
		
		//Confirm table row count
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		lecturerJdbcDaoSupport.createLecturer("L004", "Jim", "Murphy", "JimMurphy22@myCIT.ie");
		int rowCount = lecturerJdbcDaoSupport.countRows();
		assertEquals(4, rowCount);
	}
	
	//Test for Delete Lecturer method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteLecturer() {
		
		//Confirm table row count
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		lecturerJdbcDaoSupport.deleteLecturer("L001");
		int rowCount = lecturerJdbcDaoSupport.countRows();
		assertEquals(2, rowCount);
	}
	
	//Test for creating Multiple Lecturers Method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateMultipleLecturers(){
		
		//Create 3x lecturers
		Lecturer lecturer1=new Lecturer("L004","Barry","Stevens","bStevens@myCIT.ie");
		Lecturer lecturer2=new Lecturer("L005","Mary","Murphy","Mmurphy25@myCIT.ie");
		Lecturer lecturer3=new Lecturer("L006","Jim","McCarthy","JimMcCarthy89@myCIT.ie");

		//Add 3x lecturers to ArrayList
		List<Lecturer> batchLectures=new ArrayList<Lecturer>();
		batchLectures.add(lecturer1);
		batchLectures.add(lecturer2);
		batchLectures.add(lecturer3);
		
		//Confirm table row count
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		lecturerJdbcDaoSupport.createMultipleLecturers(batchLectures);
		int rowCount = lecturerJdbcDaoSupport.countRows();
		assertEquals(6, rowCount);
	}
	
	//Test for retrieving specfic Lecturer 
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGetLecturer(){
		
		//Expected Name and Surname
		String expectedLecturerName= "John";
		String expectedLecturerSurname= "Murphy";
		
		//Test Expected vs Actual(Name and Surname)
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		Lecturer actualLecturer= lecturerJdbcDaoSupport.getLecturer("L002");
		String actualLecturerName= actualLecturer.getFirstName();
		String actualLecturerSurname= actualLecturer.getLastName();
		assertEquals(expectedLecturerName, actualLecturerName);
		assertEquals(expectedLecturerSurname, actualLecturerSurname);
	}
	
	//Test for retrieving a list of Lecturers
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListLecturers(){
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		
		//Create ArrayList for expected Emails
		ArrayList<String> expectedEmails=new ArrayList<>();
		String email1="MaryGriff@myCIT.ie";
    	String email2="JohnM@myCIT.ie";
    	String email3="DaveJohnson2@myCIT.ie";
    	expectedEmails.add(email1);
        expectedEmails.add(email2);
        expectedEmails.add(email3);
        
        //Iterate through table and add actual Emails to separate ArrayList
		ArrayList<String> actualEmails = new ArrayList<>();
		List<Lecturer> lecturers=lecturerJdbcDaoSupport.listLecturers();
		for (Lecturer record : lecturers){
			actualEmails.add(record.getEmail());
		}
		//Compare both ArrayLists
		assertEquals(expectedEmails,actualEmails);
}
	
	//Test for updating existing Lecturer Email
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testUpdateEmail(){
		lecturerJdbcDaoSupport = (LecturerJdbcDaoSupport) autoWireContext.getBean("lecturerJdbcDaoSupport");
		Lecturer sampleLecturer= lecturerJdbcDaoSupport.getLecturer("L002");
		sampleLecturer.setEmail("JohnMurphy88@myCIT.ie");
		String result=sampleLecturer.toString();
		String expectedResult="Lecturer [LectId=L002, FirstName=John, LastName=Murphy, Email=JohnMurphy88@myCIT.ie]";
		assertEquals(result,expectedResult);
	}
}


