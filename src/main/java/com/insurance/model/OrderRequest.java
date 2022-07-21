package com.insurance.model;

import java.util.Objects;

public class OrderRequest {
	private String customerName;
	private String email;
	private String phoneNumber;
	private String amount;

	public OrderRequest() {

	}

	public OrderRequest(String customerName, String email, String phoneNumber, String amount) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, customerName, email, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRequest other = (OrderRequest) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(email, other.email) && Objects.equals(phoneNumber, other.phoneNumber);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderRequest [customerName=" + customerName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", amount=" + amount + "]";
	}

}
