package com.insurance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.model.Order;
import com.insurance.model.Signature;
import com.insurance.repo.OrderRepository;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public Order saveOrder(final String razorpayOrderId) {
		Order order = new Order();
		order.setRazorpayOrderId(razorpayOrderId);
//	        
		return orderRepository.save(order);
	}

	@Transactional
	public String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId,
			final String razorpaySignature, final String secret) {
		String errorMsg = null;
		try {
			Order order = orderRepository.findByRazorpayOrderId(razorpayOrderId);
			// Verify if the razorpay signature matches the generated one to
			// confirm the authenticity of the details returned
			String generatedSignature = Signature
					.calculateRFC2104HMAC(order.getRazorpayOrderId() + "|" + razorpayPaymentId, secret);
			if (generatedSignature.equals(razorpaySignature)) {
				order.setRazorpayOrderId(razorpayOrderId);
				order.setRazorpayPaymentId(razorpayPaymentId);
				order.setRazorpaySignature(razorpaySignature);
				orderRepository.save(order);
			} else {
				errorMsg = "Payment validation failed: Signature doesn't match";
			}
		} catch (Exception e) {
			log.error("Payment validation failed", e);
			errorMsg = e.getMessage();
		}
		return errorMsg;
	}
}
