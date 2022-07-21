package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@NotEmpty(message = "Please enter firstname.")
	@Column(name = "FirstName")
	private String firstName;

	@NotNull(message = "Please enter lastname.")
	@Column(name = "LastName")
	private String lastName;

	@NotEmpty(message = "Please enter username.")
	@Email(message = "Please enter valid emailid.")
	@Column(name = "UserName")
	private String userName;

	@NotEmpty(message = "Please enter password.")
	@Column(name = "Password")
	private String password;

	@NotEmpty(message = "Please enter mobile no.")
	@Column(name = "MobileNo")
	private String mobileNo;

	@NotBlank(message = "Please enter city.")
	@Column(name = "City")
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
