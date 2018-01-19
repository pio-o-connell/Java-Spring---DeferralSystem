package com.grouptwo.domain;

import javax.validation.constraints.Size;

public class Module {

	@Size(min=2, max=10, message="Module id must be not less than two and not more than 10 characters")
	private String moduleId;
	private int crnNumber;
	@Size(min=2, max=45, message="Module name must be not less than two and not more than 45 characters")
	private String name;
	private String lectId;
	private String semesterId;
	
	public Module() {
		// Blank Constructor
	}


	public Module(String moduleId, int crnNumber, String name, String lectId,
			String semesterId) {
		super();
		this.moduleId = moduleId;
		this.crnNumber = crnNumber;
		this.name = name;
		this.lectId = lectId;
		this.semesterId = semesterId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public int getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(int crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

}
