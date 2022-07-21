package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Policy;
import com.insurance.repo.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {
	private static final String ACTION_1 = "Policy not found with id %s";
	private static final String ACTION_2 = "No Policy Details Present In Database. Database is Empty . Kindly Add Policy Details..";
	private static final String ACTION_3 = "No Such Policy Details Present In Database...";
	@Autowired
	private PolicyRepository policyDao;

	@Override
	public Policy addPolicy(Policy policy) {
		return policyDao.save(policy);
	}

	@Override
	public Policy updatePolicy(Policy policy) throws NotFoundException {
		Optional<Policy> getPolicyId = policyDao.findById(policy.getId());
		if (!getPolicyId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, policy.getId()));
		}
		return policyDao.save(policy);
	}

	@Override
	public Optional<Policy> deletePolicy(int id) throws NotFoundException {

		Optional<Policy> policyId = getPolicyById(id);
		if (!policyId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));

		}
		policyDao.deleteById(id);
		return policyId;
	}

	@Override
	public Optional<Policy> getPolicyById(int id) throws NotFoundException {

		Optional<Policy> getPolicyId = policyDao.findById(id);
		if (!getPolicyId.isPresent()) {
			throw new NotFoundException(String.format(ACTION_1, id));
		}
		return getPolicyId;
	}

	@Override
	public List<Policy> getAll() throws NotFoundException {

		List<Policy> policyList = policyDao.findAll();
		if (policyList.isEmpty())
			throw new NotFoundException(String.format(ACTION_2));
		return policyList;
	}

	@Override
	public List<Policy> searchedByPolicy(String policyName) throws NotFoundException {

		List<Policy> policyList = policyDao.findByPolicyNameContaining(policyName);
		if (policyList.isEmpty())
			throw new NotFoundException(String.format(ACTION_3));
		return policyList;
	}

}
