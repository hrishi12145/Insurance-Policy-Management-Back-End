package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Admin;
import com.insurance.repo.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	private static final String ACTION_1 = "Admin not found with id %s";
	private static final String ACTION_2 = "No Admin Details Present In Database. It's Empty..";

	@Autowired
	private AdminRepository adminDao;
	

	@Override
	public Admin addAdmin(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) throws NotFoundException {
		Optional<Admin> getId = adminDao.findById(admin.getId());
		if (!getId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, admin.getId()));
		}
		return adminDao.save(admin);
	}

	@Override
	public Optional<Admin> deleteAdmin(int id) throws NotFoundException {
		Optional<Admin> userId = getAdminById(id);
		if (!userId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));
		}
		adminDao.deleteById(id);
		return userId;

	}

	@Override
	public Optional<Admin> getAdminById(int id) throws NotFoundException {
		Optional<Admin> getId = adminDao.findById(id);
		if (!getId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));
		}
		return getId;
	}

	@Override
	public List<Admin> getAll() throws NotFoundException {
		List<Admin> adminsList = adminDao.findAll();
		if (adminsList.isEmpty())
			throw new NotFoundException(String.format(ACTION_2));
		return adminsList;
	}

	@Override
	public Admin login(String userName, String password) throws NotFoundException {

		return adminDao.findByUserNameAndPassword(userName, password);

	}

	@Override
	public List<Admin> searchedByFirstName(String firstName) throws NotFoundException {
		List<Admin> adminsList = adminDao.findByFirstNameContaining(firstName);
		if (adminsList.isEmpty())
			throw new NotFoundException(String.format(ACTION_2));
		return adminsList;
	}
	

}
