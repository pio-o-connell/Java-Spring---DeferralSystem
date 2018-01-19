package com.grouptwo.mvc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.grouptwo.domain.Student;
import com.grouptwo.repository.DeferralDAO;
import com.grouptwo.repository.StudentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class StudentSeleniumTest {

	@Autowired
	StudentDAO studentDAO;
	@Autowired
	DeferralDAO deferralDAO;
	
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	
	 @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   
	  }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that the list of students is correctly displayed
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void StudentListAllSeleniumTest() throws InterruptedException{
		 driver.get(baseUrl + "/SpringWebProject/home");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div[2]/div[2]/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Students')]")).click();
		 Thread.sleep(1000);
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("List of Students", driver.findElement(By.xpath("//div[@id='pageone']/div/h4")).getText());
		 Thread.sleep(1000);
		 int numStudentsDB = studentDAO.listStudents().size();
		 int numStudentsOnScreen = driver.findElements(By.xpath("//table[@id='studentTable']/tbody/tr/td[1]/a")).size();
		 assertEquals(numStudentsDB, numStudentsOnScreen);
	 }
	 
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student can be added
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void AddNewStudentSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		// Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Student')]")).click();
		 driver.findElement(By.id("studentId")).clear();
		 driver.findElement(By.id("studentId")).sendKeys("R12345");
		 driver.findElement(By.id("firstName")).clear();
		 driver.findElement(By.id("firstName")).sendKeys("John");
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("Brown");
		 driver.findElement(By.id("email")).clear();
		 driver.findElement(By.id("email")).sendKeys("johnb@cit.ie");
		 driver.findElement(By.xpath("//input[@value='Save']")).click();
		 Thread.sleep(1000);
		 int numStudentsOnScreenAfter = driver.findElements(By.xpath("//table[@id='studentTable']/tbody/tr/td[1]/a")).size();
		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsOnScreenAfter);
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore+1);
		 Thread.sleep(1000);
		 assertEquals("John", studentDAO.getStudent("R12345").getFirstName());
		 assertEquals("Brown", studentDAO.getStudent("R12345").getLastName());
		 assertEquals("johnb@cit.ie", studentDAO.getStudent("R12345").getEmail());
		 
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("Student R12345 has been added", 
				 driver.findElement(By.xpath("//div[contains(@class, 'message')]/h4")).getText());
		 Thread.sleep(1000);
		 assertEquals("R12345", driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr["+numStudentsOnScreenAfter+"]/td[1]/a")).getText());
		 assertEquals("John", driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr["+numStudentsOnScreenAfter+"]/td[2]/h3")).getText());
		 assertEquals("Brown", driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr["+numStudentsOnScreenAfter+"]/td[3]/h3")).getText());
		 assertEquals("johnb@cit.ie", driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr["+numStudentsOnScreenAfter+"]/td[4]/h3")).getText());
		 	 
		 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student cannot be added with a duplicate id
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void AddStudentDuplicateIdSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Student')]")).click();
		 driver.findElement(By.id("studentId")).clear();
		 driver.findElement(By.id("studentId")).sendKeys("R0004444"); //same as an existing student
		 driver.findElement(By.id("firstName")).clear();
		 driver.findElement(By.id("firstName")).sendKeys("John");
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("Brown");
		 driver.findElement(By.id("email")).clear();
		 driver.findElement(By.id("email")).sendKeys("johnb@cit.ie");
		 driver.findElement(By.xpath("//input[@value='Save']")).click();
		 
		 int numStudentsOnScreenAfter = driver.findElements(By.xpath("//table[@id='studentTable']/tbody/tr/td[1]/a")).size();
		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsOnScreenAfter); //testing that number on screen matches number in DB
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore); //testing that no change in the DB
		 
		 Thread.sleep(1000);
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("Creation of new student failed.", 
				 driver.findElement(By.xpath("//div[contains(@class, 'message')]/h4")).getText());
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student cannot be added with names which are not valid
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void AddStudentInvalidSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Student')]")).click();
		 driver.findElement(By.id("studentId")).clear();
		 driver.findElement(By.id("studentId")).sendKeys("R12345");
		 driver.findElement(By.id("firstName")).clear();
		 driver.findElement(By.id("firstName")).sendKeys("J");
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("B");
		 driver.findElement(By.id("email")).clear();
		 driver.findElement(By.id("email")).sendKeys("johnb@cit.ie");
		 driver.findElement(By.xpath("//input[@value='Save']")).click();
		 
		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore); //testing that no change in the DB
		 Thread.sleep(1000);
		 assertEquals("Add New Student", driver.getTitle());
		 assertEquals("First name must be not less than two and not more than 30 characters", driver.findElement(By.id("firstName.errors")).getText());
		 assertEquals("Last name must be not less than two and not more than 30 characters", driver.findElement(By.id("lastName.errors")).getText());

	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student cannot be added with invalid email address
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void AddStudentInvalidEmailSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Student')]")).click();
		 driver.findElement(By.id("studentId")).clear();
		 driver.findElement(By.id("studentId")).sendKeys("R12345");
		 driver.findElement(By.id("firstName")).clear();
		 driver.findElement(By.id("firstName")).sendKeys("John");
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("Brown");
		 driver.findElement(By.id("email")).clear();
		 driver.findElement(By.id("email")).sendKeys("johnb");
		 driver.findElement(By.xpath("//input[@value='Save']")).click();
		 
		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore); //testing that no change in the DB
		 Thread.sleep(1000);
		 assertEquals("Add New Student", driver.getTitle());
		 
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks searching for a student by id
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void SearchStudentByIdSeleniumTest() throws InterruptedException{
		 deferralDAO.createModuleDeferral("R0020934", "COMP8038", 23317);//creating some deferrals in the database
		 deferralDAO.createModuleDeferral("R0020934", "COMP8016", 22780);
		 driver.get(baseUrl + "/SpringWebProject/home");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[3]/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Student By Id')]")).click();
		 Thread.sleep(1000);
		 new Select(driver.findElement(By.id("studentId"))).selectByVisibleText("R0020934");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='Search']")).click();
		 Thread.sleep(1000);
		 assertEquals("Display Student", driver.getTitle());
		 assertEquals("Student R0020934", driver.findElement(By.cssSelector("h4")).getText());
		 assertEquals("R0020934", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td/h3")).getText());
		 Thread.sleep(1000);
		 assertEquals("Jean", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[2]/h3")).getText());
		 assertEquals("Maguire", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[3]/h3")).getText());
		 assertEquals("KCLDC_Y4", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[4]/a")).getText());
		 Thread.sleep(1000);
		 assertEquals("jeanmaguire6@myCIT.ie", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[5]/h3")).getText());
		 assertEquals("Modules:", driver.findElement(By.xpath("//div[@id='pageone']/p")).getText());
		 assertEquals("COMP8016", driver.findElement(By.xpath("//table[@id='moduleTable']/tbody/tr/td/a")).getText());
		 assertEquals("COMP8038", driver.findElement(By.xpath("//table[@id='moduleTable']/tbody/tr[2]/td/a")).getText());
		 assertEquals("Deferrals:", driver.findElement(By.xpath("//div[@id='pageone']/p[2]")).getText());
		 Thread.sleep(1000);
		 assertEquals("COMP8038", driver.findElement(By.xpath("//table[@id='deferralTable']/tbody/tr/td[2]/a")).getText());
		 assertEquals("COMP8016", driver.findElement(By.xpath("//table[@id='deferralTable']/tbody/tr[2]/td[2]/a")).getText());
		 assertEquals("View this student's deferrals", driver.findElement(By.xpath("//div[4]/div[2]/div/div/a")).getText());
		 assertEquals("Delete student", driver.findElement(By.xpath("//div[2]/div/a[2]")).getText());



	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student can be deleted
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void DeleteStudentSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 String idDelete = "R0004444";
	 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Students')]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//a[contains(text(),'"+ idDelete+"')]")).click();
		 Thread.sleep(1000);
		 assertEquals("Display Student", driver.getTitle());
		 assertEquals("Student "+ idDelete, driver.findElement(By.cssSelector("h4")).getText());
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[@id='pageone']/a[2]")).click();
		 driver.findElement(By.xpath("//div[@id='popupDialog']/div/a[2]")).click();
		 Thread.sleep(1000);
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("Student "+ idDelete+" has been deleted", driver.findElement(By.xpath("//div[@id='pageone']/div/h4")).getText());

		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore-1); //testing that DB has one less student
		 int numStudentsOnScreenAfter = driver.findElements(By.xpath("//table[@id='studentTable']/tbody/tr/td[1]/a")).size();
		 assertEquals(numStudentsDBAfter, numStudentsOnScreenAfter); //testing that screen displays same number of students as DB
		 Thread.sleep(1000);
		 List <Student> studentList = studentDAO.listStudents();
		 List<String> idList = new ArrayList<String>();
		 for(Student student: studentList){
			 idList.add(student.getStudentId());
		 }
		 assertFalse(idList.contains(idDelete)); //testing that no student exists with id just deleted
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student is not deleted when Cancel is selected in the popup
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void DeleteStudentCancelSeleniumTest() throws InterruptedException{
		 
		 int numStudentsDBBefore = studentDAO.listStudents().size();
		 String idDelete = "R0004444";
	 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'Students')]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//a[contains(text(),'"+ idDelete+"')]")).click();
		 Thread.sleep(1000);
		 assertEquals("Display Student", driver.getTitle());
		 assertEquals("Student "+ idDelete, driver.findElement(By.cssSelector("h4")).getText());
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//div[@id='pageone']/a[2]")).click();
		 driver.findElement(By.xpath("//div[@id='popupDialog']/div/a")).click();
		 Thread.sleep(1000);
		 assertEquals("Display Student", driver.getTitle());
		 assertEquals("Student "+ idDelete, driver.findElement(By.cssSelector("h4")).getText());
		 Thread.sleep(1000);
		 int numStudentsDBAfter = studentDAO.listStudents().size();
		 assertEquals(numStudentsDBAfter, numStudentsDBBefore); //testing that DB has the same number of students
		
		 Thread.sleep(1000);
		 List <Student> studentList = studentDAO.listStudents();
		 List<String> idList = new ArrayList<String>();
		 for(Student student: studentList){
			 idList.add(student.getStudentId());
		 }
		 assertTrue(idList.contains(idDelete)); //testing that the student still exists 
		
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks first that a student's details as displayed match the DB and then
	  * that a student's last name can be modified
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void ModifyStudentSeleniumTest() throws InterruptedException{
		 
		 String idModify = "R0004444";
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("(//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a)")).click();
		 driver.findElement(By.xpath("(//a[contains(text(),'Students')])")).click();
		 Thread.sleep(1000);
		 
		 //checking that details on screen are as in DB
		 assertEquals(idModify, driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td/a")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getFirstName(),
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[2]/h3")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getLastName(), 
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[3]/h3")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getEmail(),
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[4]/h3")).getText());
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[5]/a")).click();
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("Walsh");
		 driver.findElement(By.id("modify")).click();
		 Thread.sleep(1000);
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("Student "+idModify+" has been modified", driver.findElement(By.cssSelector("h4")).getText());
		 assertEquals(idModify, driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td/a")).getText());
		 Thread.sleep(1000);
		 
		 //checking that new last name appears on screen 
		 assertEquals("Walsh", driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[3]/h3")).getText());
		 assertEquals("Walsh", studentDAO.getStudent(idModify).getLastName()); //checking that last name has been modified in DB
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student's last name can be modified with an invalid last name
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void ModifyLongNameStudentSeleniumTest() throws InterruptedException{
		 
		 String idModify = "R0004444";
		 String originalLastname = studentDAO.getStudent(idModify).getLastName();
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.linkText("Menu Options")).click();
		 driver.findElement(By.xpath("(//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a)")).click();
		 driver.findElement(By.xpath("(//a[contains(text(),'Students')])")).click();
		 Thread.sleep(1000);
		 
		 //checking that details on screen are as in DB
		 assertEquals(idModify, driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td/a")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getFirstName(),
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[2]/h3")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getLastName(), 
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[3]/h3")).getText());
		 assertEquals(studentDAO.getStudent(idModify).getEmail(),
				 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[4]/h3")).getText());
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[5]/a")).click();
		 driver.findElement(By.id("lastName")).clear();
		 driver.findElement(By.id("lastName")).sendKeys("O'donoghuefdghjfghjfghjfdghjdgfdjgfdjfghjfghfdghjfdjjh");
		 driver.findElement(By.id("modify")).click();
		 Thread.sleep(1000);
		 assertEquals("Students List", driver.getTitle());
		 assertEquals("Modification of student "+idModify+" failed", driver.findElement(By.xpath("//div[@id='pageone']/div/h4")).getText());
		 assertEquals(idModify, driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td/a")).getText());
		 Thread.sleep(1000);
		 
		 //checking that unchanged last name is displayed on screen
		 assertEquals(originalLastname, driver.findElement(By.xpath("//table[@id='studentTable']/tbody/tr/td[3]/h3")).getText());
		 //checking that original last name is still in DB
		 assertEquals(originalLastname, studentDAO.getStudent(idModify).getLastName());
		  
	 
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student's programme and module(s) are displayed correctly
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void ViewStudentProgrammeModulesSeleniumTest() throws InterruptedException{
		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.xpath("(//a[contains(text(),'Menu Options')])")).click();
		 driver.findElement(By.xpath("(//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a)")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("(//a[contains(text(),'Students')])")).click();
		 driver.findElement(By.xpath("(//a[contains(text(),'R0004444')])")).click();
		 Thread.sleep(1000);
		 assertEquals("Display Student", driver.getTitle());
		 assertEquals("R0004444", driver.findElement(By.xpath("//div[4]/div[2]/div/table/tbody/tr/td/h3")).getText());
		 assertEquals("KCMSD_Y5", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[4]/a")).getText());
		 assertEquals("COMP8040", driver.findElement(By.xpath("//table[@id='moduleTable']/tbody/tr/td/a")).getText());

		 
		 
	 }
	 
	 /**
	  * @author Julia Foden
	  * @throws InterruptedException
	  * This test checks that a student's deferrals are displayed correctly
	  */
	 @Test
	 @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	 public void ViewStudentDeferralsSeleniumTest() throws InterruptedException{
		 deferralDAO.createModuleDeferral("R0020934", "COMP8038", 23317);//creating some deferrals in the database
		 deferralDAO.createModuleDeferral("R0020934", "COMP8016", 22780);		 
		 driver.get(baseUrl + "SpringWebProject/");
		 driver.findElement(By.xpath("(//a[contains(text(),'Menu Options')])")).click();
		 driver.findElement(By.xpath("(//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a)")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("(//a[contains(text(),'Students')])")).click();
		 driver.findElement(By.xpath("//a[contains(text(),'R0020934')]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.linkText("View this student's deferrals")).click();
		 Thread.sleep(1000);
		 assertEquals("Deferrals List", driver.getTitle());
		 assertEquals("R0020934", driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[2]/a")).getText());
		 int numDeferralsOnScreen =  driver.findElements(By.xpath("//table[@id='myTable']/tbody/tr/td[2]/a")).size();
		 int numDeferralsDB = deferralDAO.listDeferralsByStudent("R0020934").size();
		 //checking that the number of deferrals on screen matches the DB
		 assertEquals(numDeferralsDB, numDeferralsOnScreen);
		 
	 }	 
	 
	 @After
		public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	 }
}
