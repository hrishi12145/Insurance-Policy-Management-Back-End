package com.insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	public List<Policy> findByPolicyNameContaining(@Param("policyName") String policyName);

}