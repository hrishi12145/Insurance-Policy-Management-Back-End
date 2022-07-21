package com.insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserNameAndPassword(String userName, String password);

	public List<User> findByFirstNameContaining(@Param("firstName") String firstName);
}
