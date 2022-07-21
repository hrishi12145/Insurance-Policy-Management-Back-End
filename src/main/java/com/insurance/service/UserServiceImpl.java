package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.User;
import com.insurance.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final String ACTION_1 = "User not found with id %s";
	private static final String ACTION_2 = "No User Details Present In Database. It's Empty..";

	@Autowired
	private UserRepository userDao;

	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUser(User user) throws NotFoundException {
		Optional<User> getId = userDao.findById(user.getId());
		if (!getId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, user.getId()));
		}
		return userDao.save(user);
	}

	@Override
	public Optional<User> deleteUser(int id) throws NotFoundException {

		Optional<User> userId = getUserById(id);
		if (!userId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));

		}
		userDao.deleteById(id);
		return userId;
	}

	@Override
	public Optional<User> getUserById(int id) throws NotFoundException {

		Optional<User> getUserId = userDao.findById(id);
		if (!getUserId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));
		}
		return getUserId;
	}

	@Override
	public List<User> getAll() throws NotFoundException {

		List<User> usersList = userDao.findAll();
		if (usersList.isEmpty())
			throw new NotFoundException(String.format(ACTION_2));
		return usersList;
	}

	@Override
	public User login(String userName, String password) throws NotFoundException {

		return userDao.findByUserNameAndPassword(userName, password);
	}

	@Override
	public List<User> searchedByFirstName(String firstName) throws NotFoundException {
		List<User> searchedList = userDao.findByFirstNameContaining(firstName);
		if (searchedList.isEmpty())
			throw new NotFoundException(String.format(ACTION_2));
		return searchedList;
	}

}