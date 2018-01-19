package com.grouptwo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
	
	@Size(min=2, max=20, message="First name must be not less than two and not more than 30 characters")
	private String firstName;
	@Size(min=2, max=30, message="Last name must be not less than two and not more than 30 characters")
	private String lastName;
	@Size (min=8, max=30, message="Email address must be not less than eight and not more than 30 characters")
	private String email;

	public Person() {
		// Blank Contructor
	}

	public Person(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
