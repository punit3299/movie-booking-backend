package com.cg.movie.services;


import java.sql.Timestamp;
import java.time.Instant;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	ShowRepository showRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/*
	 * Function to Add New Customer
	 */
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	/*
	 * Function to Check if Customer Exists or Not
	 */
	@Override
	public Customer findCustomerById(long customerId) {
		
		boolean checkCustomer= customerRepo.existsById(customerId);
		
		if(checkCustomer==false)
		{
			logger.error("Customer not found with customerId: "+customerId);
			throw new CustomerNotFoundException("Customer Not Found");
		}
		else {
			Customer customer=customerRepo.getOne(customerId);
			logger.info(" Customer found with customerId:"+customerId);
			return customer;
		}
		
	}
	
	/*
	 * Function to Add Money to Wallet
	 */
	@Override
	public Customer addMoneyToWallet(Customer customer, int amount) {
		
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		
		return customerRepo.save(customer);
	}
	
	/*
	 * Function to Refund Money to wallet
	 */
	@Override
	public Customer refundMoneyToWallet(Customer customer,long showId, int amount) {
		
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		
		Show show=showRepo.getOne(showId);
		
		Transaction transaction = new Transaction();
		transaction.setTransactionMessage("Rs. "+amount+" refunded to Wallet regarding show: "+show.getShowName());
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		transaction.setShow(show);
		
		transactionRepo.save(transaction);
		
		return customerRepo.save(customer);
	}

	
	
	
	

}
