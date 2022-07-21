package com.insurance.model;

import java.util.Objects;

public class PaymentResponse {
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;

	public PaymentResponse() {

	}

	public PaymentResponse(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
		super();
		this.razorpayOrderId = razorpayOrderId;
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpaySignature = razorpaySignature;
	}

	@Override
	public int hashCode() {
		return Objects.hash(razorpayOrderId, razorpayPaymentId, razorpaySignature);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentResponse other = (PaymentResponse) obj;
		return Objects.equals(razorpayOrderId, other.razorpayOrderId)
				&& Objects.equals(razorpayPaymentId, other.razorpayPaymentId)
				&& Objects.equals(razorpaySignature, other.razorpaySignature);
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getRazorpaySignature() {
		return razorpaySignature;
	}

	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}

	@Override
	public String toString() {
		return "PaymentResponse [razorpayOrderId=" + razorpayOrderId + ", razorpayPaymentId=" + razorpayPaymentId
				+ ", razorpaySignature=" + razorpaySignature + "]";
	}

}
