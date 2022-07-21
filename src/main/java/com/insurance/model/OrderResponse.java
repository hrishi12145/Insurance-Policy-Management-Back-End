package com.insurance.model;

import java.util.Objects;

public class OrderResponse {
	private String applicationFee;
	private String razorpayOrderId;
	private String secretKey;

	public OrderResponse() {

	}

	public OrderResponse(String applicationFee, String razorpayOrderId, String secretKey) {
		super();
		this.applicationFee = applicationFee;
		this.razorpayOrderId = razorpayOrderId;
		this.secretKey = secretKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationFee, razorpayOrderId, secretKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderResponse other = (OrderResponse) obj;
		return Objects.equals(applicationFee, other.applicationFee)
				&& Objects.equals(razorpayOrderId, other.razorpayOrderId) && Objects.equals(secretKey, other.secretKey);
	}

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return "OrderResponse [applicationFee=" + applicationFee + ", razorpayOrderId=" + razorpayOrderId
				+ ", secretKey=" + secretKey + "]";
	}

}
