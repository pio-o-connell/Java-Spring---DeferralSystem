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

import com.grouptwo.domain.Registration;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:configuration.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class RegistrationJdbcDaoSupportTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	RegistrationJdbcDaoSupport registrationJdbcDaoSupport;


	//Test for retrieving list of Registrations
	@Test
	@DatabaseSetup(value = "classpath:databaseEntries.xml", type = DatabaseOperation.CLEAN_INSERT)
	public void testListRegistrations(){
		registrationJdbcDaoSupport = (RegistrationJdbcDaoSupport) autoWireContext.getBean("registrationJdbcDaoSupport");
		List<Registration> registrations=registrationJdbcDaoSupport.listRegistrations();
		assertEquals(registrations.size(), 3);	
		assertEquals(registrations.get(0).getStudentId(), "R0020934");
		assertEquals(registrations.get(0).getProgrammeId(), "KCLDC_Y4");

	}
}
