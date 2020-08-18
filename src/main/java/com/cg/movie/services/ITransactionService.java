package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Transaction;

public interface ITransactionService {

	public Transaction addTransaction(Transaction transnaction);
	
	public List<Transaction> getCustomerTransactions(long customerId);
}
