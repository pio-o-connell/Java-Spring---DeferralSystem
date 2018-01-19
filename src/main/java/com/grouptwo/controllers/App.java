package com.grouptwo.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.grouptwo.repository.ProgrammeJdbcDaoSupport;

public class App {

	private static ApplicationContext appContext;
	
	 public static void main(String[] args){
	    	
	    	appContext = new ClassPathXmlApplicationContext("configuration.xml");
	    	
	    	ProgrammeJdbcDaoSupport pds = (ProgrammeJdbcDaoSupport)appContext.getBean("programmeJdbcDaoSupport");
	    	pds.createProgramme("KCMSD_Y5", 5, "L001", "Jan");
	    	//pds.deleteProgramme("KCMSD_Y5");
	 }
}
