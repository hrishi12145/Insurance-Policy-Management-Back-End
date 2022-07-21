package com.insurance.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Policy;
import com.insurance.model.Transaction;
import com.insurance.model.User;
import com.insurance.service.PolicyService;
import com.insurance.service.TransactionService;
import com.insurance.service.UserService;

@RestController
@RequestMapping({ "/insurance" })
@CrossOrigin("*")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserService userService;

	@Autowired
	public PolicyService policyService;

	@Autowired
	public TransactionService transactionService;

	@PostMapping("/user/register")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		log.debug("User has been Created");
		User userAdd = userService.addUser(user);
		return new ResponseEntity<>(userAdd, HttpStatus.CREATED);

	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Valid @RequestBody User user)
			throws NotFoundException {
		user.setId(id);
		User updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	@PostMapping("/user/login")
	public ResponseEntity<User> login(@RequestBody User user) throws NotFoundException {
		User login = userService.login(user.getUserName(), user.getPassword());
		return new ResponseEntity<>(login, HttpStatus.OK);

	}

	@PostMapping("/user/logout")
	public ResponseEntity<String> logout() {

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/user/policy/getPolicies")
	public ResponseEntity<List<Policy>> getAllPolicies() throws NotFoundException {
		List<Policy> policies = policyService.getAll();
		return new ResponseEntity<>(policies, HttpStatus.OK);

	}

	@GetMapping("/user/policy/searchedByPolicy/{keyword}")
	public ResponseEntity<List<Policy>> searchedByPolicy(@PathVariable("keyword") String policyName)
			throws NotFoundException {
		List<Policy> policiesSearchedByName = policyService.searchedByPolicy(policyName);
		return new ResponseEntity<>(policiesSearchedByName, HttpStatus.OK);

	}

	@GetMapping("/user/policy/getPolicyById/{id}")
	public ResponseEntity<Optional<Policy>> getPolicyById(@PathVariable("id") int id) throws NotFoundException {
		Optional<Policy> policyById = policyService.getPolicyById(id);
		return new ResponseEntity<>(policyById, HttpStatus.OK);

	}

	@PostMapping("/user/purchasePolicy")
	public ResponseEntity<Transaction> purchasePolicy(@RequestBody Transaction transaction) {
		transaction.setPurchaseDate(LocalDateTime.now());
		return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);

	}

	@GetMapping("/user/transaction/getTransactionById/{id}")
	public ResponseEntity<Optional<Transaction>> findUserTransactions(@PathVariable("id") int id)
			throws NotFoundException {
		Optional<Transaction> userTransactionsById = transactionService.findUserTransactions(id);
		return new ResponseEntity<>(userTransactionsById, HttpStatus.OK);

	}

}
