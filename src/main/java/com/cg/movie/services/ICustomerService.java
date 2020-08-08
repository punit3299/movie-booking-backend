package com.cg.movie.services;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public void addMoneyToWallet(Customer customer,int money);
	
	public void refundMoneyToWallet(Customer customer,int amount);

}
