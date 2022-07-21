package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Transaction;
import com.insurance.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	private static final String ACTION_1 = "No Transaction Details Present In Database. It's Empty..";
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public int numberOfTransactions() {
		return (int) transactionRepository.count();

	}

	@Override
	public List<Transaction> findAllTransactions() throws NotFoundException {
		List<Transaction> transactionsList = transactionRepository.findAll();
		if (transactionsList.isEmpty())
			throw new NotFoundException(String.format(ACTION_1));
		return transactionsList;
	}

	@Override
	public Optional<Transaction> findUserTransactions(int id) throws NotFoundException {
		Optional<Transaction> transactionsList = transactionRepository.findById(id);
		if (transactionsList.isEmpty())
			throw new NotFoundException(String.format(ACTION_1));
		return transactionsList;
	}
}
