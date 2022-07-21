package com.insurance.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.RazorPayClientConfig;
import com.insurance.model.OrderRequest;
import com.insurance.model.OrderResponse;
import com.insurance.model.PaymentResponse;
import com.insurance.service.OrderService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/insurance")
@CrossOrigin("*")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private RazorpayClient client;

	private RazorPayClientConfig razorPayClientConfig;

	@Autowired
	private OrderService orderService;

	@Autowired
	public OrderController(RazorPayClientConfig razorpayClientConfig) throws RazorpayException {
		this.razorPayClientConfig = razorpayClientConfig;
		this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
	}

	@PostMapping("/order")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) { // @CurrentUser LocalUser
																								// user
		OrderResponse razorPay = null;
		try {
			// The transaction amount is expressed in the currency subunit, such
			// as paise (in case of INR)
			String amountInPaise = convertRupeeToPaise(orderRequest.getAmount());
			// Create an order in RazorPay and get the order id
			com.razorpay.Order order = createRazorPayOrder(amountInPaise);
			razorPay = getOrderResponse((String) order.get("id"), amountInPaise);
			// Save order in the database
			orderService.saveOrder(razorPay.getRazorpayOrderId()); // , user.getUser().getId()
		} catch (RazorpayException e) {
			log.error("Exception while create payment order", e);

		}
		return ResponseEntity.ok(razorPay);
	}

	@PutMapping("/order")
	public ResponseEntity<PaymentResponse> updateOrder(@RequestBody PaymentResponse paymentResponse) {
		String errorMsg = orderService.validateAndUpdateOrder(paymentResponse.getRazorpayOrderId(),
				paymentResponse.getRazorpayPaymentId(), paymentResponse.getRazorpaySignature(),
				razorPayClientConfig.getSecret());
		if (errorMsg != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return null;

	}

	private OrderResponse getOrderResponse(String orderId, String amountInPaise) {
		OrderResponse razorPay = new OrderResponse();
		razorPay.setApplicationFee(amountInPaise);
		razorPay.setRazorpayOrderId(orderId);
		razorPay.setSecretKey(razorPayClientConfig.getKey());
		return razorPay;
	}

	private com.razorpay.Order createRazorPayOrder(String amount) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		return client.Orders.create(options);
	}

	private String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
	}
}
