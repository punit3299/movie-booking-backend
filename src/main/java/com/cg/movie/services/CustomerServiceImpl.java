package com.cg.movie.services;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.exception.CustomerExistsException;
import com.cg.movie.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/*
	 * Function to Check Existing use while Adding
	 */
	@Override
	public Customer checkNewCustomer(long customerId) {
		
		Customer customer= customerRepo.findById(customerId).get();
		if(customer!=null)
		{
			logger.error("Customer already exists with customerId: "+customerId);
			throw new CustomerExistsException("Customer Already Exists");
		}
		else {
			logger.info(" Customer Added with customerId: "+customerId);
			return customer;
		}
		
	}
	
	/*
	 * Function to Add New Customer
	 */
	@Override
	public Customer addCustomer(Customer customer) {
		Customer newCustomer=checkNewCustomer(customer.getCustomerId());
		return customerRepo.save(newCustomer);
	}
	
	/*
	 * Function to Check if Customer Exists or Not
	 */
	@Override
	public Customer findCustomerById(long customerId) {
		
		Customer customer= customerRepo.findById(customerId).get();
		if(customer==null)
		{
			logger.error("Customer not found with customerId: "+customerId);
			throw new CustomerNotFoundException("Customer Not Found");
		}
		else {
			logger.info(" Customer found with id customerId:"+customerId);
			return customer;
		}
		
	}
	
	/*
	 * Function to Add Money to Wallet
	 */
	@Override
	public Customer addMoneyToWallet(Customer customer, int money) {
		customer.setCustomerBalance(customer.getCustomerBalance()+money);
		return customerRepo.save(customer);
	}
	
	/*
	 * Function to Refund Money to wallet
	 */
	@Override
	public Customer refundMoneyToWallet(Customer customer, int amount) {
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		return customerRepo.save(customer);
	}

	
	
	
	

}
