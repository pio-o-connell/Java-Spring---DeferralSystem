package com.grouptwo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.grouptwo.domain.Lecturer;
import com.grouptwo.repository.LecturerDAO;



/**
 * The Class LecturerTest.
 * @author Dale Cusack
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })

public class LecturerTest {

	/** The LecturerDAO. */
	@Autowired
	LecturerDAO lecturerDAO;

	/** The driver. */
	private WebDriver driver;

	/** The base url. */
	private String baseUrl;

	/** The verification errors. */
	private StringBuffer verificationErrors = new StringBuffer();


	/**
	 * Set up.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	/**
	 * Test create new lecturer test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewLecturerTestCase() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Lecturer")).click();
		driver.findElement(By.id("lectId")).clear();
		driver.findElement(By.id("lectId")).sendKeys("L004");
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Sarah");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("O'Brien");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("sarahobrien@cit.ie");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		//instructing Selenium to wait for following page to load by searching for element on next page
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("lecturerTable")));

		Lecturer actualLecturer= lecturerDAO.getLecturer("L004");

		//Expected Name and Surname
		String expectedLecturerName= "Sarah";
		String expectedLecturerSurname= "O'Brien";

		//Test Expected vs Actual(Name and Surname)
		String actualLecturerName= actualLecturer.getFirstName();
		String actualLecturerSurname= actualLecturer.getLastName();
		assertEquals(expectedLecturerName, actualLecturerName);
		assertEquals(expectedLecturerSurname, actualLecturerSurname);


	}

	/**
	 * Test modify lecturer.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testModifyLecturer() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Lecturers")).click();
		driver.findElement(By.linkText("Modify")).click();
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Walsh");
		driver.findElement(By.id("modify")).click();

		Lecturer actualLecturer= lecturerDAO.getLecturer("L001");

		//Expected Name and Surname
		String expectedLecturerName= "Mary";
		String expectedLecturerSurname= "Walsh";

		//Test Expected vs Actual(Name and Surname)
		String actualLecturerName= actualLecturer.getFirstName();
		String actualLecturerSurname= actualLecturer.getLastName();
		assertEquals(expectedLecturerName, actualLecturerName);
		assertEquals(expectedLecturerSurname, actualLecturerSurname);

	}

	/**
	 * Test delete lecturer.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteLecturer() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Lecturers")).click();
		driver.findElement(By.linkText("L001")).click();
		driver.findElement(By.linkText("Delete lecturer")).click();
		driver.findElement(By.linkText("Delete")).click();

		//page loading not quick enough for execution,to slow down execution so changes come into effect 
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			WebElement elementa = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTable")));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		List<Lecturer> lecturers=lecturerDAO.listLecturers();

		assertEquals(2, lecturers.size());
	}
	

	/**
	 * Test create new lecturer Failed test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewLecturerFailedTestCase()  {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Lecturer")).click();
		driver.findElement(By.id("lectId")).clear();
		driver.findElement(By.id("lectId")).sendKeys("L");
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Mary");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Griffin");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("MaryGriff@myCIT.ie");


		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		assertEquals("Lecturer id must be not less than two and not more than four characters", 
				driver.findElement(By.id("lectId.errors")).getText());

	}
	
	/**
	 * @author Julia Foden
	 * @throws InterruptedException
	 * This test checks that the list of lecturers is correctly displayed, matching the database
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void LecturerListAllSeleniumTest() throws InterruptedException{
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Lecturers')]")).click();

		assertEquals("Lecturers List", driver.getTitle());
		assertEquals("List of Lecturers", driver.findElement(By.xpath("//div[@id='pageone']/div/h4")).getText());
		Thread.sleep(1000);
		int numLecDB = lecturerDAO.listLecturers().size();
		int numLecOnScreen = driver.findElements(By.xpath("//table[@id='lecturerTable']/tbody/tr/td[1]/a")).size();
		assertEquals(numLecDB, numLecOnScreen);
	}
	
	/**
	 * @author Julia Foden
	 * @throws InterruptedException
	 * This test checks that a lecturer cannot be added with a duplicate id
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void AddLecturerDuplicateIdSeleniumTest() throws InterruptedException{

		int numLecDBBefore = lecturerDAO.listLecturers().size();

		driver.get(baseUrl + "SpringWebProject/");
		driver.findElement(By.linkText("Menu Options")).click();
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Lecturer')]")).click();
		driver.findElement(By.id("lectId")).clear();
		driver.findElement(By.id("lectId")).sendKeys("L001"); //same as an existing lecturer
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("John");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Brown");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("johnb@cit.ie");
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		int numLecOnScreenAfter = driver.findElements(By.xpath("//table[@id='lecturerTable']/tbody/tr/td[1]/a")).size();
		int numLecDBAfter = lecturerDAO.listLecturers().size();
		assertEquals(numLecDBAfter, numLecOnScreenAfter); //testing that number on screen matches number in DB
		assertEquals(numLecDBAfter, numLecDBBefore); //testing that no change in the DB

		Thread.sleep(1000);
		assertEquals("Lecturers List", driver.getTitle());
		assertEquals("Creation of new lecturer failed.", 
				driver.findElement(By.xpath("//div[contains(@class, 'message')]/h4")).getText());
	}
	
	/**
	 * @author Julia Foden
	 * @throws InterruptedException
	 * This test checks that a lecturer cannot be added with invalid email address
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void AddLecturerInvalidEmailSeleniumTest() throws InterruptedException{

		int numLecDBBefore = lecturerDAO.listLecturers().size();

		driver.get(baseUrl + "SpringWebProject/");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div/h1/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Lecturer')]")).click();
		driver.findElement(By.id("lectId")).clear();
		driver.findElement(By.id("lectId")).sendKeys("L010");
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("John");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Brown");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("johnb");
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		int numLecDBAfter = lecturerDAO.listLecturers().size();
		assertEquals(numLecDBAfter, numLecDBBefore); //testing that no change in the DB
		Thread.sleep(1000);
		assertEquals("Add New Lecturer", driver.getTitle());

	}

	
	/**
	 * @author Julia Foden
	 * @throws InterruptedException
	 * This test checks searching for a lecturer by id
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void SearchLecturerByIdSeleniumTest() throws InterruptedException{

		String id = "L001"; //the lecturer id to search for

		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[3]/h1/a")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Lecturer')])[3]")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("lectId"))).selectByVisibleText(id);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		Thread.sleep(1000);
		assertEquals("Display Lecturer", driver.getTitle());
		assertEquals("Lecturer "+id, driver.findElement(By.cssSelector("h4")).getText());
		assertEquals(id, driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td/h3")).getText());
		Thread.sleep(1000);
		// testing that the  details displayed match what is in the DB
		assertEquals(lecturerDAO.getLecturer(id).getFirstName(), driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[2]/h3")).getText());
		assertEquals(lecturerDAO.getLecturer(id).getLastName(), driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[3]/h3")).getText());
		Thread.sleep(1000);
		assertEquals(lecturerDAO.getLecturer(id).getEmail(), driver.findElement(By.xpath("//table[@id='myTable']/tbody/tr/td[4]/h3")).getText());
		assertEquals("Delete lecturer", driver.findElement(By.xpath("//div[2]/div/a[2]")).getText());


	}
	
	/**
	 * @author Julia Foden
	 * @throws InterruptedException
	 * This test checks that a lecturer is not deleted when cancel is selected in the popup
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void DeleteLecturerCancelSeleniumTest() throws InterruptedException{

		int numLecDBBefore = lecturerDAO.listLecturers().size();
		String idDelete = "L001";

		driver.get(baseUrl + "SpringWebProject/");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.xpath("//div[@id='myPanelDefault']/div/div/div/div[2]/h1/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Lecturers')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'"+ idDelete+"')]")).click();
		Thread.sleep(1000);
		assertEquals("Display Lecturer", driver.getTitle());
		assertEquals("Lecturer "+ idDelete, driver.findElement(By.cssSelector("h4")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='pageone']/a[2]")).click();
		driver.findElement(By.xpath("//div[@id='popupDialog']/div/a")).click();
		Thread.sleep(1000);
		assertEquals("Display Lecturer", driver.getTitle());
		assertEquals("Lecturer "+ idDelete, driver.findElement(By.cssSelector("h4")).getText());
		Thread.sleep(1000);

		int numLecDBAfter = lecturerDAO.listLecturers().size();
		assertEquals(numLecDBAfter, numLecDBBefore); //testing that DB has the same  number of lecturers

		List <Lecturer> lectList = lecturerDAO.listLecturers();
		List<String> idList = new ArrayList<String>();
		for(Lecturer lecturer: lectList){
			idList.add(lecturer.getLectId());
		}
		assertTrue(idList.contains(idDelete)); //testing that the lecturer still exists 


	}

	/**
	 * Tear down.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}

	}

}
