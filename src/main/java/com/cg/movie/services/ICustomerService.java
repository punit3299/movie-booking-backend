package com.cg.movie.services;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer addMoneyToWallet(Customer customer,int money);
	
	public Customer refundMoneyToWallet(Customer customer,int amount);

}
