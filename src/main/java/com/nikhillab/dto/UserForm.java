package com.nikhillab.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {

	@NotBlank(message = "UserName is required")
	private String name;

	@Email(message = "Invalid email")
	private String email;

	@Size(min = 6)
	private String password;

	private String about;

	@Size(min = 8, max = 12, message = "Invalid phone number")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", phone=" + phone + "]";
	}

}
