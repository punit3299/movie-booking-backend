package com.cg.movie.services;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer findCustomerById(long customerId);
	
	public Customer addMoneyToWallet(Customer customer,int money);
	
	public Customer refundMoneyToWallet(Customer customer,long showId,int amount);
	public int getBalance(Long customerId);

}
