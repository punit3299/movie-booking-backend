package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Customer;
import com.cg.movie.response.LoginCredential;

public interface ICustomerService {
	
	public Customer addCustomer(Customer customer);
	
	public Customer findCustomerById(long customerId);
	
	public Customer addMoneyToWallet(Customer customer,int money);
	
	public Customer refundMoneyToWallet(Customer customer,long showId,double amount);
	public double getBalance(Long customerId);

	public List<Customer> getAllCustomer();
	
	public boolean findEmailIfExists(String email);
	
	public boolean findContactNoIfExists(long contactNumber);
	
	public String validateCredential(LoginCredential credentials);
	
	public Customer findCustomerByEmail(String email);
}
