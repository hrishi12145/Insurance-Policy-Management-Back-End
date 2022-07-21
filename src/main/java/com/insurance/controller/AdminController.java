package com.insurance.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Admin;
import com.insurance.model.Policy;
import com.insurance.model.Transaction;
import com.insurance.model.User;
import com.insurance.service.AdminService;
import com.insurance.service.PolicyService;
import com.insurance.service.TransactionService;
import com.insurance.service.UserService;

@RestController
@RequestMapping({ "/insurance" })
@CrossOrigin("*")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	public PolicyService policyService;

	@Autowired
	public UserService userService;

	@Autowired
	public TransactionService transactionService;

	@GetMapping("/admin/getAdmins")
	public ResponseEntity<List<Admin>> getAllAdmins() throws NotFoundException {
		log.debug("Get All Admin Details");
		List<Admin> admins = adminService.getAll();
		return new ResponseEntity<>(admins, HttpStatus.OK);

	}

	@GetMapping("/admin/searchedByName/{keyword}")
	public ResponseEntity<List<Admin>> searchedByAdminFirstName(@PathVariable("keyword") String firstName)
			throws NotFoundException {
		List<Admin> users = adminService.searchedByFirstName(firstName);
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/admin/getAdminsById/{id}")
	public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable("id") int id) throws NotFoundException {
		Optional<Admin> adminById = adminService.getAdminById(id);
		return new ResponseEntity<>(adminById, HttpStatus.OK);

	}

	@PostMapping("/admin/register")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
		Admin adminAdd = adminService.addAdmin(admin);
		return new ResponseEntity<>(adminAdd, HttpStatus.CREATED);

	}

	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<Optional<Admin>> deleteAdmin(@PathVariable int id) throws NotFoundException {
		Optional<Admin> adminDelete = adminService.deleteAdmin(id);
		return new ResponseEntity<>(adminDelete, HttpStatus.NO_CONTENT);

	}

	@PutMapping("/admin/update/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") int id, @Valid @RequestBody Admin admin)
			throws NotFoundException {
		admin.setId(id);
		Admin update = adminService.updateAdmin(admin);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@PostMapping("/admin/login")
	public ResponseEntity<Admin> login(@RequestBody Admin admin) throws NotFoundException {
		Admin login = adminService.login(admin.getUserName(), admin.getPassword());
		return new ResponseEntity<>(login, HttpStatus.OK);

	}

	@PostMapping("/admin/user/logout")
	public ResponseEntity<String> logout() {

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/admin/policy/getPolicies")
	public ResponseEntity<List<Policy>> getAllPolicies() throws NotFoundException {
		List<Policy> policies = policyService.getAll();
		return new ResponseEntity<>(policies, HttpStatus.OK);

	}

	@GetMapping("/admin/policy/getPolicyById/{id}")
	public ResponseEntity<Optional<Policy>> getPolicyById(@PathVariable("id") int id) throws NotFoundException {
		Optional<Policy> policyById = policyService.getPolicyById(id);
		return new ResponseEntity<>(policyById, HttpStatus.OK);

	}

	@PostMapping("/admin/policy/register")
	public ResponseEntity<Policy> addPolicy(@Valid @RequestBody Policy policy) {
		Policy policyAdd = policyService.addPolicy(policy);
		return new ResponseEntity<>(policyAdd, HttpStatus.CREATED);

	}

	@DeleteMapping("/admin/policy/delete/{id}")
	public ResponseEntity<Optional<Policy>> deletePolicy(@PathVariable int id) throws NotFoundException {
		Optional<Policy> policyDelete = policyService.deletePolicy(id);
		return new ResponseEntity<>(policyDelete, HttpStatus.NO_CONTENT);

	}

	@PutMapping("/admin/policy/update/{id}")
	public ResponseEntity<Policy> updatePolicy(@PathVariable("id") int id, @Valid @RequestBody Policy policy)
			throws NotFoundException {

		policy.setId(id);
		Policy updatePolicy = policyService.updatePolicy(policy);
		return new ResponseEntity<>(updatePolicy, HttpStatus.OK);

	}

	@GetMapping("/admin/user/getUsers")
	public ResponseEntity<List<User>> getAllUsers() throws NotFoundException {
		List<User> users = userService.getAll();
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/admin/user/searchedByName/{keyword}")
	public ResponseEntity<List<User>> searchedByUserFirstName(@PathVariable("keyword") String firstName)
			throws NotFoundException {
		List<User> users = userService.searchedByFirstName(firstName);
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	@GetMapping("/admin/user/getUsersById/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id) throws NotFoundException {
		Optional<User> userById = userService.getUserById(id);
		return new ResponseEntity<>(userById, HttpStatus.OK);

	}

	@DeleteMapping("/admin/user/delete/{id}")
	public ResponseEntity<Optional<User>> deleteUser(@PathVariable int id) throws NotFoundException {
		Optional<User> userDelete = userService.deleteUser(id);
		return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);

	}

	@PutMapping("/admin/user/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Valid @RequestBody User user)
			throws NotFoundException {
		user.setId(id);
		User updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	@GetMapping("/admin/transaction/getAll")
	public ResponseEntity<List<Transaction>> getAllTransactions() throws NotFoundException {
		List<Transaction> getAllTransactions = transactionService.findAllTransactions();
		return new ResponseEntity<>(getAllTransactions, HttpStatus.OK);

	}

}
