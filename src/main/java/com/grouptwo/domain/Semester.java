package com.grouptwo.domain;

public class Semester {

	private String semesterId;
	private String programmeId;
	private String startMonth;
	private String endMonth;

	public Semester() {
		// Blank Constructor
	}

	public Semester(String semesterId, String programmeId, String startMonth,
			String endMonth) {
		super();
		this.semesterId = semesterId;
		this.programmeId = programmeId;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

}
