package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Transaction;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;

	@Override
	public Transaction addTransaction(Transaction transaction) {
		
		return transactionRepo.save(transaction);
	}

}
