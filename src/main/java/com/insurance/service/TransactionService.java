package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.insurance.exception.NotFoundException;
import com.insurance.model.Transaction;

@Service
public interface TransactionService {

	public Transaction saveTransaction(Transaction transaction);

	public int numberOfTransactions();

	public List<Transaction> findAllTransactions() throws NotFoundException;

	Optional<Transaction> findUserTransactions(int id) throws NotFoundException;

}
