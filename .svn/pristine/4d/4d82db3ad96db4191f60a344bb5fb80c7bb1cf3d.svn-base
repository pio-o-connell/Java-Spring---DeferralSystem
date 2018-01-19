package com.grouptwo.mvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.grouptwo.domain.Programme;
import com.grouptwo.repository.ProgrammeDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class LoginTest.
 * @author Dale Cusack
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class LoginTest {


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
	 * Sets the up.
	 *
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
	 * Test login.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testLogin() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Programme")).click();
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("L001");
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("123456");
		driver.findElement(By.name("submit")).click();

		//page loading not quick enough for execution,to slow down execution so changes come into effect 
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			WebElement elementa = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Menu Options")));
		} catch (Exception e) {
			//e.printStackTrace();
		}

		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Programme")).click();

		driver.findElement(By.id("programmeId")).sendKeys("KCLDC_Y6");

		WebElement programmeId= driver.findElement(By.id("programmeId"));
		Assert.assertEquals(true, programmeId.isDisplayed());
		Assert.assertEquals(true,isElementPresent(By.id("programmeId")));

		new Select(driver.findElement(By.id("coordinatorId"))).selectByVisibleText("L002");
		driver.findElement(By.id("numYears")).clear();
		driver.findElement(By.id("numYears")).sendKeys("4");
		driver.findElement(By.id("progYear")).clear();
		driver.findElement(By.id("progYear")).sendKeys("4");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div/div/div[5]/div[2]/input")).click();

		//instructing Selenium to wait for following page to load by searching for element on next page
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("myTable")));

		List<Programme> programmes=programmeDAO.listProgrammes();
		assertEquals(6,programmes.size());
	}

	/**
	 * Tear down.
	 *
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


