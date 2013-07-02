package com.rohlx.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.bval.constraints.Email;
import org.apache.bval.constraints.NotEmpty;

public class RequestForm extends BaseForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty(message="Please provide a name")
	private String name;
	
	@NotNull
	@NotEmpty(message="Please provide a phone number")
	private String phone;
	
	@Email(message="Please provide a valid email")
	private String email;
	
	@NotNull
	@NotEmpty(message="Please enter the message")
	@Size(min=20, max=200, message="Message should be between 20 to 200 words")
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String comment) {
		this.message = comment;
	}
	

}
