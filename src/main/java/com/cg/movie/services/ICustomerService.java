package com.cg.movie.services;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer findCustomerById(long customerId);
	
	public Customer addMoneyToWallet(long customerId,int money);
	
	public Customer refundMoneyToWallet(long customerId,int amount);

}
