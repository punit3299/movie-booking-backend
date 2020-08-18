package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Customer;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer findCustomerById(long customerId);
	
	public Customer addMoneyToWallet(Customer customer,int money);
	
<<<<<<< HEAD
	public Customer refundMoneyToWallet(Customer customer,long showId,int amount);
	public int getBalance(Long customerId);
=======
	public Customer refundMoneyToWallet(Customer customer,long showId,double amount);
>>>>>>> 369505d2ff1048a26a5d3ced9396541f22335eb5

	public List<Customer> getAllCustomer();
}
