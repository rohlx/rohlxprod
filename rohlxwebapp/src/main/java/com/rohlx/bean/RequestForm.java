package com.rohlx.bean;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.bval.constraints.Email;
import org.apache.bval.constraints.NotEmpty;

public class RequestForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty(message="Please provide a name")
	private String name;
	
	@NotNull
	@NotEmpty(message="Please provide a phone number")
	@Size(min=10,max=10,message="Please provide a valid phone number")
	@Digits(integer=10,fraction=0,message="Please provide a valid phone number")
	private String phone;
	
	@Email(message="Please provide a valid email")
	private String email;
	
	@NotNull
	@NotEmpty(message="Please enter the message")
	@Size(min=20, max=200, message="Message should be between 20 to 200 words")
	private String message;
	
	private String requestNumber;
	
	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

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
