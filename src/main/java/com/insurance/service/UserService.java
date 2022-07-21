package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.User;

@Service
public interface UserService {

	public User addUser(User user);

	public User updateUser(User user) throws NotFoundException;

	public Optional<User> deleteUser(int id) throws NotFoundException;

	public Optional<User> getUserById(int id) throws NotFoundException;

	public List<User> getAll() throws NotFoundException;

	public User login(String userName, String password) throws NotFoundException;

	public List<User> searchedByFirstName(String firstName) throws NotFoundException;
}
