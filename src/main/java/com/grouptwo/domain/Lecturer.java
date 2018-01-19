package com.grouptwo.domain;

import javax.validation.constraints.Size;

public class Lecturer extends Person {

	@Size(min=2, max=4, message="Lecturer id must be not less than two and not more than four characters")
	private String lectId;

	public Lecturer() {
		// blank constructor
	}

	public Lecturer(String lectId, String firstName, String lastName,
			String email) {
		super(firstName, lastName, email);
		this.lectId = lectId;

	}

	public String getLectId() {
		return lectId;
	}

	public void setLectId(String lectId) {
		this.lectId = lectId;
	}

	@Override
	public String toString() {
		return "Lecturer [LectId=" + getLectId() + ", FirstName="
				+ getFirstName() + ", LastName=" + getLastName() + ", Email="
				+ getEmail() + "]";
	}

}