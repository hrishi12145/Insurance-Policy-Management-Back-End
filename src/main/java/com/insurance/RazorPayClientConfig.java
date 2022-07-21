package com.insurance;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "razorpay")
public class RazorPayClientConfig {
	private String key;
	private String secret;

	public RazorPayClientConfig() {

	}

	public RazorPayClientConfig(String key, String secret) {
		super();
		this.key = key;
		this.secret = secret;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, secret);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RazorPayClientConfig other = (RazorPayClientConfig) obj;
		return Objects.equals(key, other.key) && Objects.equals(secret, other.secret);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "RazorPayClientConfig [key=" + key + ", secret=" + secret + "]";
	}

}
