package com.cg.movie.services;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	@Override
	public Customer findCustomerById(long customerId) {
		
		Customer customer= customerRepo.getOne(customerId);
		if(customer==null)
		{
			logger.error("Customer not found with "+customerId);
			throw new CustomerNotFoundException("Customer Not Found");
		}
		else {
			logger.info(" Customer found with id "+customerId);
			return customer;
		}
		
	}

	@Override
	public Customer addMoneyToWallet(long customerId, int money) {
		Customer customer= findCustomerById(customerId);
		customer.setCustomerBalance(customer.getCustomerBalance()+money);
		return customerRepo.save(customer);
	}

	@Override
	public Customer refundMoneyToWallet(long customerId, int amount) {
		Customer customer= findCustomerById(customerId);
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		return customerRepo.save(customer);
	}

	
	
	
	

}
