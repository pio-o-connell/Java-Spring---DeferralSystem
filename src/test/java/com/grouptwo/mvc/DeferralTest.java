package com.grouptwo.mvc;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
import com.grouptwo.domain.Deferral;
import com.grouptwo.repository.DeferralDAO;



// TODO: Auto-generated Javadoc
/**
 * @author Dale Cusack
 * The Class DeferralTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class DeferralTest {
	
	/** The DeferralDAO. */
	@Autowired
	DeferralDAO deferralDAO;
	
	/** The driver. */
	private WebDriver driver;
	
	/** The base url. */
	private String baseUrl;
	
	/** The verification errors. */
	private StringBuffer verificationErrors = new StringBuffer();

	  /**
  	 * Set up.
  	 *@author Dale Cusack
  	 * @throws Exception the exception
  	 */
  	@Before
	  public void setUp() throws Exception {
		FirefoxProfile fxProfile = new FirefoxProfile();
		fxProfile.setPreference("browser.helperapps.neverAsk.saveToDisk", "application/pdf");
	    driver = new FirefoxDriver(fxProfile);
	    driver.manage().window().maximize();
	    baseUrl = "http://localhost:8080/";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }
	
	  /**
  	 * Test create deferral.
  	 * @author Dale Cusack
  	 * @throws Exception the exception
  	 */
  	@Test
	  @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	  public void testCreateDeferral() throws Exception {
		
	    driver.get(baseUrl + "/SpringWebProject/home");
	    driver.findElement(By.linkText("Download Deferral Form")).click();
	    
	    driver.findElement(By.linkText("Menu Options")).click();
	    driver.findElement(By.partialLinkText("Apply for")).click();
	    driver.findElement(By.linkText("Deferral by Module")).click();
	    driver.findElement(By.id("studentId")).clear();
	    driver.findElement(By.id("studentId")).sendKeys("R0004444");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

	    driver.findElement(By.id("file")).click();
	    WebElement elem = driver.findElement(By.cssSelector("input[type='file']"));
	    elem.sendKeys("C:\\Users\\Dale\\Downloads\\Withdrawal_Form.pdf");
	    
	    //Setting file path to StringSelection Object in clipboard
	    StringSelection ss=new StringSelection("C:\\Users\\Dale\\Downloads\\Withdrawal_Form.pdf");
	    //file path content in clipboard
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	  
	    /*Code to control uploader window which is out of Seleniums reach
	     * ElementNotVisibleException: Element is not currently visible and so may not be interacted with
	     * Perform native keystrokes for CTRL+V and CTRL+ENTER
	     */
	    Robot robot = new Robot();
	    robot.delay(1000);
	    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
	    robot.keyPress(java.awt.event.KeyEvent.VK_V);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_V);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
	    robot.delay(1000);
	    
	    new Select(driver.findElement(By.id("moduleId"))).selectByVisibleText("Cloud Application Frameworks");
	    new Select(driver.findElement(By.id("programmeId"))).selectByVisibleText("24248");
	    
	    //getting Selenium to properly click on the "Save" button.Neither sendKeys() or type() was working
	    WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div/div[4]/div[2]/input"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element); 
	      
	    //instructing Selenium to wait for following page to load by searching for element on next page
	    WebDriverWait wait = new WebDriverWait(driver, 40);
	    WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("myTable")));
	   
	    int rowCount = deferralDAO.countRows();
		assertEquals(3, rowCount);

		// Confirm correct Module Codes;
		List<Deferral> studentDeferrals = deferralDAO.listDeferralsByStudent("R0004444");
		assertEquals("COMP8040", studentDeferrals.get(0).getModuleId());
		assertEquals("KCMSD_Y5",studentDeferrals.get(0).getProgrammeId());
	  }
	
	  /**
  	 * Test create deferral prog.
  	 * @author Dale Cusack
  	 * @throws Exception the exception
  	 */
  	@Test
	  @DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	  public void testCreateDeferralProg() throws Exception {
	    driver.get(baseUrl + "/SpringWebProject/home");
	    driver.findElement(By.linkText("Download Deferral Form")).click();
	    driver.findElement(By.linkText("Menu Options")).click();
	    driver.findElement(By.partialLinkText("Apply for")).click();
	    driver.findElement(By.linkText("Deferral by Programme")).click();
	    driver.findElement(By.id("studentId")).clear();
	    driver.findElement(By.id("studentId")).sendKeys("R0004444");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    
	    driver.findElement(By.id("file")).click();
	    WebElement elem = driver.findElement(By.cssSelector("input[type='file']"));
	    elem.sendKeys("C:\\Users\\Dale\\Downloads\\Withdrawal_Form.pdf");
	    
	    StringSelection ss=new StringSelection("C:\\Users\\Dale\\Downloads\\Withdrawal_Form.pdf");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null); 
	    
	    Robot robot = new Robot();
	    robot.delay(1000);
	    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
	    robot.keyPress(java.awt.event.KeyEvent.VK_V);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_V);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
	    robot.delay(1000);
	    
	    new Select(driver.findElement(By.id("programmeId"))).selectByVisibleText("KCMSD_Y5");
	    
	    WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/form/div/div[3]/div[2]/input"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element); 
	    
	    WebDriverWait wait = new WebDriverWait(driver, 40);
	    WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("myTable")));
	    
	    int rowCount = deferralDAO.countRows();
		assertEquals(3, rowCount);
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
