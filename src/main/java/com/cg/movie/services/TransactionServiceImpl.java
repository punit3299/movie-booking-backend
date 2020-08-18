package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Transaction;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;

	/********************************************************************************
	 * 
	 * Method : addTransaction
	 * 
	 * Description: for adding transaction
	 * 
	 * @param  : transaction object
	 * 
	 * @return : transaction Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,11 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Transaction addTransaction(Transaction transaction) {
		
		return transactionRepo.save(transaction);
	}
	
	
	/********************************************************************************
	 * 
	 * Method : getCustomerTransactions
	 * 
	 * Description: for getting customer transactions
	 * 
	 * @param  : customerId 		Customer customerId 
	 * 
	 * @return : List<Transaction>
	 * 
	 *         Created by: Siddharth Raghuvanshi ,11 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> getCustomerTransactions(long customerId) {
		
		List<Transaction> transactionList=transactionRepo.getAllTransactions(customerId);
		
		return transactionList;
	}

}
