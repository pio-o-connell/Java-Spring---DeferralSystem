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

import com.grouptwo.domain.Student;
import com.grouptwo.repository.StudentJdbcDaoSupport;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class StudentJdbcDaoSupportTest {
	@Autowired
	ApplicationContext autoWireContext;
	StudentJdbcDaoSupport studentJdbcDaoSupport;
	

	//Test for Create Student method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateStudent() {
		
		//Confirm table row count
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		studentJdbcDaoSupport.createStudent("R0008181", "Fiona", "Lynch", "FionaLynch2@myCIT.ie");
		int rowCount = studentJdbcDaoSupport.countRows();
		assertEquals(3, rowCount);
	}
	
	//Test for Delete Student method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteStudent() {
		
		//Confirm table row count
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		studentJdbcDaoSupport.deleteStudent("R0004444");
		int rowCount = studentJdbcDaoSupport.countRows();
		assertEquals(1, rowCount);
	}
	
	//Test for creating Multiple Students
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateMultipleStudents(){
		
		//Create 3x Students
		Student student1=new Student("R0423744","Tim","Brookes","timbrookes@myCIT.ie");
		Student student2=new Student("R0087945","Barry","Burton","barryburton5@myCIT.ie");
		Student student3=new Student("R0099946","Rebecca","Chambers","rebeccachambers11@myCIT.ie");

		//Add 3x Students to ArrayList
		List<Student> batchLectures=new ArrayList<Student>();
		batchLectures.add(student1);
		batchLectures.add(student2);
		batchLectures.add(student3);
		
		//Confirm table row count
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		studentJdbcDaoSupport.createMultipleStudents(batchLectures);
		int rowCount = studentJdbcDaoSupport.countRows();
		assertEquals(5, rowCount);
	}
	
	//Test for retrieving specfic Student
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testGetStudent(){
		
		//Expected String Values
		String expectedStudentName= "Jean";
		String expectedStudentSurname= "Maguire";
		
		//Actual String Values
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		Student actualStudent= studentJdbcDaoSupport.getStudent("R0020934");
		String actualStudentName= actualStudent.getFirstName();
		String actualStudentSurname= actualStudent.getLastName();
		
		//Compare Expected vs Actual
		assertEquals(expectedStudentName, actualStudentName);
		assertEquals(expectedStudentSurname, actualStudentSurname);
	}
	
	//Test for retrieving list of Students
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListStudents(){
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		
		//Create ArrayList for expected Emails
		ArrayList<String> expectedEmails=new ArrayList<>();
		String email1="billodonoghue17@myCIT.ie";
    	String email2="jeanmaguire6@myCIT.ie";
    	expectedEmails.add(email1);
        expectedEmails.add(email2);
        
        //Iterate through table and add actual Emails to separate ArrayList
		ArrayList<String> actualEmails = new ArrayList<>();
		List<Student> students=studentJdbcDaoSupport.listStudents();
		for (Student record : students){
			actualEmails.add(record.getEmail());
		}
		//Compare both ArrayLists
		assertEquals(expectedEmails,actualEmails);
}
	
	//Test for updating existing Student Email
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testUpdateEmail(){
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		Student sampleStudent= studentJdbcDaoSupport.getStudent("R0020934");
		sampleStudent.setEmail("jeanmaguire6@myCIT.ie");
		String result=sampleStudent.toString();
		String expectedResult="Student [StudId=R0020934, FirstName=Jean, LastName=Maguire, Email=jeanmaguire6@myCIT.ie]";
		assertEquals(result,expectedResult);
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void updateStudentTest(){
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		studentJdbcDaoSupport.updateStudent("R0004444", "Johnny", "Jennings", "jonnyjennings@gmail.com");
		assertEquals("Johnny", studentJdbcDaoSupport.getStudent("R0004444").getFirstName());
		assertEquals("Jennings", studentJdbcDaoSupport.getStudent("R0004444").getLastName());
		assertEquals("jonnyjennings@gmail.com", studentJdbcDaoSupport.getStudent("R0004444").getEmail());
		
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void deleteMultipleStudentsTest(){
		//set up by adding more students to the database
		studentJdbcDaoSupport = (StudentJdbcDaoSupport) autoWireContext.getBean("studentJdbcDaoSupport");
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student("R0012333", "Peter", "Pepper", "fdsf");
		Student student2 = new Student("R0012344", "Paul", "Pepper", "fdsf");
		Student student3 = new Student("R0012355", "Paloma", "Pepper", "fdsf");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		
		studentJdbcDaoSupport.createMultipleStudents(students);
		
		int numBefore = studentJdbcDaoSupport.countRows();
		
		List<Student> studentsToBeDeleted = new ArrayList<Student>();
		studentsToBeDeleted.add(studentJdbcDaoSupport.getStudent("R0012333"));
		studentsToBeDeleted.add(studentJdbcDaoSupport.getStudent("R0012344"));
		
		studentJdbcDaoSupport.deleteMultipleStudents(studentsToBeDeleted);
		
		int numAfter = studentJdbcDaoSupport.countRows();
		
		assertEquals(numAfter, numBefore-2);
	
	}
	
}
