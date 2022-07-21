package com.insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByUserNameAndPassword(String userName, String password);

	public List<Admin> findByFirstNameContaining(@Param("firstName") String firstName);

}
