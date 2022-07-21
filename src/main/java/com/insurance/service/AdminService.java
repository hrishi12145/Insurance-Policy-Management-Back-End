package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Admin;

@Service
public interface AdminService {

	public Admin addAdmin(Admin admin);

	public Admin updateAdmin(Admin admin) throws NotFoundException;

	public Optional<Admin> deleteAdmin(int id) throws NotFoundException;

	public Optional<Admin> getAdminById(int id) throws NotFoundException;

	public List<Admin> getAll() throws NotFoundException;

	public Admin login(String userName, String password) throws NotFoundException;

	public List<Admin> searchedByFirstName(String firstName) throws NotFoundException;
}
