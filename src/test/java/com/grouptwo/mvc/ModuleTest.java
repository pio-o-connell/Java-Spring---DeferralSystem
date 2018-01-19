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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.grouptwo.domain.Module;
import com.grouptwo.repository.ModuleDAO;




/**
 * The Class ModuleTest.
 * @author Dale Cusack
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })


public class ModuleTest {

	/** The moduleDAO. */
	@Autowired
	ModuleDAO moduleDAO;

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Test create new module.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewModule() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Module")).click();
		driver.findElement(By.id("moduleId")).clear();
		driver.findElement(By.id("moduleId")).sendKeys("COMP8080");
		driver.findElement(By.id("crnNumber")).clear();
		driver.findElement(By.id("crnNumber")).sendKeys("22222");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Java");
		new Select(driver.findElement(By.id("lectId"))).selectByVisibleText("L002");
		new Select(driver.findElement(By.id("semesterId"))).selectByVisibleText("KCLDC_Y4_S1");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		//instructing Selenium to wait for following page to load by searching for element on next page
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("myTable")));

		//Expected String Values
		String expectedModuleName= "Java";
		String expectedModuleSemester= "KCLDC_Y4_S1";
		String expectedModuleLecturer="L002";

		//Actual String Values
		Module actualModule= moduleDAO.getModule("COMP8080", 22222);
		String actualModuleName= actualModule.getName();
		String actualModuleSemester= actualModule.getSemesterId();
		String actualModuleLecturer= actualModule.getLectId();

		//Compare Expected vs Actual
		assertEquals(expectedModuleName, actualModuleName);
		assertEquals(expectedModuleSemester, actualModuleSemester);
		assertEquals(expectedModuleLecturer, actualModuleLecturer);
	}

	/**
	 * Test modify module.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testModifyModule() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Modules")).click();
		driver.findElement(By.linkText("Modify")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Advanced Business Intelligence");
		driver.findElement(By.id("modify")).click();

		//Expected String Values
		String expectedModuleName= "Advanced Business Intelligence";
		String expectedModuleSemester= "KCLDC_Y4_S1";
		String expectedModuleLecturer="L002";

		//Actual String Values
		Module actualModule= moduleDAO.getModule("COMP8016", 22780);
		String actualModuleName= actualModule.getName();
		String actualModuleSemester= actualModule.getSemesterId();
		String actualModuleLecturer= actualModule.getLectId();

		//Compare Expected vs Actual
		assertEquals(expectedModuleName, actualModuleName);
		assertEquals(expectedModuleSemester, actualModuleSemester);
		assertEquals(expectedModuleLecturer, actualModuleLecturer);
	}

	/**
	 * Test delete module.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testDeleteModule() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("List All")).click();
		driver.findElement(By.linkText("Modules")).click();
		driver.findElement(By.linkText("Delete")).click();

		//page loading not quick enough for execution,to slow down execution so changes come into effect 
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			WebElement elementa = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTable")));
		}catch (Exception e) {
			//e.printStackTrace();
		}

		List<Module> modules=moduleDAO.listModules();

		assertEquals(3, modules.size());

	}

	/**
	 * Test create new module.
	 * @author Dale Cusack
	 * @throws Exception the exception
	 */
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateNewModuleFailed() throws Exception {
		driver.get(baseUrl + "/SpringWebProject/home");
		driver.findElement(By.linkText("Menu Options")).click();
		driver.findElement(By.partialLinkText("Add New")).click();
		driver.findElement(By.linkText("Module")).click();
		driver.findElement(By.id("moduleId")).clear();
		driver.findElement(By.id("moduleId")).sendKeys("C");
		driver.findElement(By.id("crnNumber")).clear();
		driver.findElement(By.id("crnNumber")).sendKeys("22222");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("J");
		new Select(driver.findElement(By.id("lectId"))).selectByVisibleText("L002");
		new Select(driver.findElement(By.id("semesterId"))).selectByVisibleText("KCLDC_Y4_S1");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		assertEquals("Module id must be not less than two and not more than 10 characters", 
				driver.findElement(By.id("moduleId.errors")).getText());
		assertEquals("Module name must be not less than two and not more than 45 characters", 
				driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div/div/div[3]/span")).getText());


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
