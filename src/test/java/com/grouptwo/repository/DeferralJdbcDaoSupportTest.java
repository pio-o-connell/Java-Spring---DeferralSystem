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

import com.grouptwo.domain.Deferral;
import com.grouptwo.domain.Module;
import com.grouptwo.repository.DeferralJdbcDaoSupport;
import com.grouptwo.repository.ModuleJdbcDaoSupport;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class DeferralJdbcDaoSupportTest {

	@Autowired
	ApplicationContext autoWireContext;
	DeferralJdbcDaoSupport deferralJdbcDaoSupport;
	ModuleJdbcDaoSupport moduleJdbcDaoSupport;
	RegistrationJdbcDaoSupport registrationJdbcDaoSupport;

	// Test for Create Module Deferral method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateModuleDeferral() {
		
		//Confirm table row count
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createModuleDeferral("R0004444", "COMP8040",24248);
		int rowCount = deferralJdbcDaoSupport.countRows();
		assertEquals(1, rowCount);

		// Confirm correct lecturer Id
		moduleJdbcDaoSupport = (ModuleJdbcDaoSupport) autoWireContext.getBean("moduleJdbcDaoSupport");
		Module module = moduleJdbcDaoSupport.getModule("COMP8040", 24248);
		assertEquals("L001", module.getLectId());

	}

	// Test for Create Programme Deferral method
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testCreateProgrammeDeferral() {
		
		//Confirm table row count
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		moduleJdbcDaoSupport = (ModuleJdbcDaoSupport) autoWireContext.getBean("moduleJdbcDaoSupport");
		moduleJdbcDaoSupport.registerStudentForModule("R0004444", 24249);
		deferralJdbcDaoSupport.createProgrammeDeferral("R0004444", "KCMSD_Y5");
		int rowCount = deferralJdbcDaoSupport.countRows();
		assertEquals(2, rowCount);

		// Confirm correct Module Codes;
		List<Deferral> studentDeferrals = deferralJdbcDaoSupport.listDeferralsByStudent("R0004444");
		assertEquals("COMP8040", studentDeferrals.get(0).getModuleId());
		assertEquals("COMP8016", studentDeferrals.get(1).getModuleId());

	}

	// Test for List Deferrals Methods
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListDeferrals() {
		
		//Confirm table row count
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		List<Deferral> deferrals = deferralJdbcDaoSupport.listDeferrals();
		assertEquals(deferrals.size(), 2);
		int rowCount = deferralJdbcDaoSupport.countRows();
		assertEquals(2, rowCount);

	}

	// Test for List Deferrals corresponding to a Student
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListStudentDeferrals() {
		
		//Confrim List size and ProgrammeId
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		List<Deferral> deferrals = deferralJdbcDaoSupport.listDeferralsByStudent("R0020934");
		assertEquals(deferrals.size(), 2);
		assertEquals(deferrals.get(0).getProgrammeId(), "KCLDC_Y4");
	}

	// Test for approving a student's Module Deferral
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testApproveModuleDeferral() {

		// Test that the entries now contain "True" instead of "False"
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		deferralJdbcDaoSupport.approveModuleDeferral("R0020934", "COMP8038");
		deferralJdbcDaoSupport.approveModuleDeferral("R0020934", "COMP8016");
		List<Deferral> deferrals = deferralJdbcDaoSupport.listDeferralsByStudent("R0020934");
		assertEquals(deferrals.get(0).getApproval(), "True");
		assertEquals(deferrals.get(1).getApproval(), "True");

		 /*Test that the entires have been deleted from the student's
		 registration
		moduleJdbcDaoSupport = (ModuleJdbcDaoSupport) autoWireContext.getBean("moduleJdbcDaoSupport");
		registrationJdbcDaoSupport = (RegistrationJdbcDaoSupport) autoWireContext.getBean("registrationJdbcDaoSupport");
		int rowCount = registrationJdbcDaoSupport.countRows();
		assertEquals(1, rowCount);*/

	}

	// Test for approving a student's Programme Deferral
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testApproveProgrammeDeferral() {

		// Test that the entries now contain "True" instead of "False"
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		deferralJdbcDaoSupport.approveProgrammeDeferral("R0020934", "KCLDC_Y4");
		List<Deferral> deferrals = deferralJdbcDaoSupport.listDeferralsByStudent("R0020934");
		assertEquals(deferrals.get(0).getApproval(), "True");
		assertEquals(deferrals.get(1).getApproval(), "True");

		/*Test that the entires have been deleted from the student's
		registration*/
		moduleJdbcDaoSupport = (ModuleJdbcDaoSupport) autoWireContext.getBean("moduleJdbcDaoSupport");
		registrationJdbcDaoSupport = (RegistrationJdbcDaoSupport) autoWireContext.getBean("registrationJdbcDaoSupport");
		int rowCount = registrationJdbcDaoSupport.countRows();
		assertEquals(1, rowCount);
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListDeferralsByProgramme() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		List<Deferral> deferralList = deferralJdbcDaoSupport.listDeferralsByProgramme("KCLDC_Y4");
		assertEquals(2, deferralList.size());
	}

	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListUnapprovedDeferrals() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		List<Deferral> deferralList = deferralJdbcDaoSupport.listDeferralsByProgramme("KCLDC_Y4");
		assertEquals(2, deferralList.size());
	}

	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListUnapprovedDeferralsByProgramme() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		int id = deferralJdbcDaoSupport.createDeferralGetId("R0004444", "L001", "KCMSD_Y5", "COMP8040");
		deferralJdbcDaoSupport.approveModuleDeferral("R0004444", "COMP8040");
		List<Deferral> deferralList = deferralJdbcDaoSupport.listDeferrals();
		List<Deferral> unapprovedDeferralList = deferralJdbcDaoSupport.listUnapprovedDeferralsByProgramme("False", "KCMSD_Y5");
		
		assertEquals(unapprovedDeferralList.size(), deferralList.size()-1);
	}

	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListUnapprovedDeferralsByProgrammeCoordinatorName() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		List<Deferral> unapprovedDeferralList = deferralJdbcDaoSupport.
				listUnapprovedDeferralsByProgrammeCoordinatorName("False", "Larkin");
		assertEquals(3, unapprovedDeferralList.size()+3);
	}

	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListApprovedDeferralsByStudent() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createDeferralGetId("R0004444", "L001", "KCMSD_Y5", "COMP8040");
		deferralJdbcDaoSupport.approveModuleDeferral("R0004444", "COMP8040");
		List<Deferral> deferralList = deferralJdbcDaoSupport.listApprovedDeferralsByStudent("True", "R0012346");
		assertEquals(1, deferralList.size()+1);
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteDeferralById() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		int id = deferralJdbcDaoSupport.createDeferralGetId("R0004444", "L001", "KCMSD_Y5", "COMP8040");
		int numBefore = deferralJdbcDaoSupport.countRows();
		deferralJdbcDaoSupport.deleteDeferralById(id);
		int numAfter = deferralJdbcDaoSupport.countRows();
		
		assertEquals(numAfter, numBefore-1);
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteDeferralsByStudentID() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.deleteDeferralsByStudentID("R0020934");
		assertEquals(0, deferralJdbcDaoSupport.countRows());
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteDeferralsByModuleID() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createProgrammeDeferral("R0020934", "KCLDC_Y4");
		deferralJdbcDaoSupport.deleteDeferralsByModuleID("COMP8038");
		assertEquals(1, deferralJdbcDaoSupport.countRows());
	}
	
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testCreateDeferralGetId() {
		deferralJdbcDaoSupport = (DeferralJdbcDaoSupport) autoWireContext.getBean("deferralJdbcDaoSupport");
		deferralJdbcDaoSupport.createDeferralGetId("R0004444", "L001", "KCMSD_Y5", "COMP8040");
		assertEquals(1, deferralJdbcDaoSupport.countRows());
		
		
	}

}