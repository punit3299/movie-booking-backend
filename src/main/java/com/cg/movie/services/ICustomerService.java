package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer findCustomerById(long customerId);
	
	public Customer addMoneyToWallet(Customer customer,int money);
	
	public Customer refundMoneyToWallet(Customer customer,long showId,int amount);

	public List<Customer> getAllCustomer();
}
