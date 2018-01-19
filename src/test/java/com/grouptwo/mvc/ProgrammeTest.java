package com.grouptwo.mvc;

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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.grouptwo.domain.Programme;
import com.grouptwo.repository.ProgrammeDAO;



/**
 * The Class ProgrammeTest.
 * @author Dale Cusack
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })

public class ProgrammeTest  {//extends FirefoxDriver

	/** The programmeDAO. */
	@Autowired
	ProgrammeDAO programmeDAO;

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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}


	/**
	 * Test create new programme test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewProgrammeTestCase() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Programme")).click();
		driver.findElement(By.id("programmeId")).clear();
		driver.findElement(By.id("programmeId")).sendKeys("KCLDC_Y6");

		WebElement programmeId= driver.findElement(By.id("programmeId"));
		assertEquals(true, programmeId.isDisplayed());
		assertEquals(true,isElementPresent(By.id("programmeId")));

		new Select(driver.findElement(By.id("coordinatorId"))).selectByVisibleText("L002");
		driver.findElement(By.id("numYears")).clear();
		driver.findElement(By.id("numYears")).sendKeys("4");
		driver.findElement(By.id("progYear")).clear();
		driver.findElement(By.id("progYear")).sendKeys("4");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		//instructing Selenium to wait for following page to load by searching for element on next page
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("myTable")));

		assertEquals("Programme List",driver.getTitle());
		assertEquals("Programme KCLDC_Y6 has been added",driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/h4")).getText());

		List<Programme> programmes=programmeDAO.listProgrammes();
		assertEquals(6,programmes.size());

		assertEquals( "KCLDC_Y4",programmes.get(0).getProgrammeId());
		assertEquals( "KCLDC_Y6_Y1",programmes.get(1).getProgrammeId());
		assertEquals( "KCLDC_Y6_Y2",programmes.get(2).getProgrammeId());
		assertEquals( "KCLDC_Y6_Y3",programmes.get(3).getProgrammeId());
		assertEquals( "KCLDC_Y6_Y4",programmes.get(4).getProgrammeId());
		assertEquals( "KCMSD_Y5",programmes.get(5).getProgrammeId());

	}

	/**
	 * Test delete programme test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteProgrammeTestCase() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Programmes")).click();
		driver.findElement(By.linkText("Delete")).click();

		//page loading not quick enough for execution,to slow down execution so changes come into effect 
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			WebElement elementa = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTable")));
		} catch (Exception e) {
			//e.printStackTrace();
		}


		List<Programme> programmesAfterDelete=programmeDAO.listProgrammes();
		assertEquals(1,programmesAfterDelete.size());
	}

	/**
	 * Test modify programme test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testModifyProgrammeTestCase() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Programmes")).click();
		driver.findElement(By.linkText("Modify")).click();

		new Select(driver.findElement(By.id("coordinatorId"))).selectByVisibleText("L001");
		driver.findElement(By.id("modify")).click();

		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			WebElement elementa = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTable")));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		List<Programme> programmesAfterModify=programmeDAO.listProgrammes();
		assertEquals( 1,programmesAfterModify.size());
		assertEquals( "L001",programmesAfterModify.get(0).getCoordinatorId());
	}

	/**
	 * Test create new programme test case.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewProgrammeFailedTestCase()  {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Programme")).click();
		driver.findElement(By.id("programmeId")).clear();
		driver.findElement(By.id("programmeId")).sendKeys("KCMSD_Y5_Y1");

		WebElement programmeId= driver.findElement(By.id("programmeId"));
		assertEquals(true, programmeId.isDisplayed());
		assertEquals(true,isElementPresent(By.id("programmeId")));

		new Select(driver.findElement(By.id("coordinatorId"))).selectByVisibleText("L002");
		driver.findElement(By.id("numYears")).clear();
		driver.findElement(By.id("numYears")).sendKeys("4");
		driver.findElement(By.id("progYear")).clear();
		driver.findElement(By.id("progYear")).sendKeys("4");


		try {
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		} catch (DuplicateKeyException e) {
			//e.printStackTrace();
		}

		assertEquals("A Programme id cannot have less than 5 characters or more than 8", 
				driver.findElement(By.id("programmeId.errors")).getText());





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

	/**
	 * Checks if is element present.
	 * @author Dale Cusack
	 * @param by the by
	 * @return true, if is element present
	 */
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
