package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Policy;

@Service
public interface PolicyService {

	public Policy addPolicy(Policy policy);

	public Policy updatePolicy(Policy policy) throws NotFoundException;

	public Optional<Policy> deletePolicy(int id) throws NotFoundException;

	public Optional<Policy> getPolicyById(int id) throws NotFoundException;

	public List<Policy> getAll() throws NotFoundException;

	public List<Policy> searchedByPolicy(String policyName) throws NotFoundException;

}
