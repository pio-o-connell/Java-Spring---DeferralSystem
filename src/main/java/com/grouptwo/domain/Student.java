package com.grouptwo.domain;

import javax.validation.constraints.Size;

public class Student extends Person {

	@Size(min=2, max=12, message="Student id must be not less than two and not more than 12 characters")
	private String studentId;

	public Student() {
		// Blank Constructor
	}

	public Student(String studentId, String firstName, String lastName,
			String email) {
		super(firstName, lastName, email);
		this.studentId = studentId;

	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Student [StudId=" + getStudentId() + ", FirstName="
				+ getFirstName() + ", LastName=" + getLastName() + ", Email="
				+ getEmail() + "]";
	}

}
