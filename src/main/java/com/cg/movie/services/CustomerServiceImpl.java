package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.entities.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer addMoneyToWallet(Customer customer, int money) {
		customer.setCustomerBalance(customer.getCustomerBalance()+money);
		return customerRepo.save(customer);
	}

	@Override
	public Customer refundMoneyToWallet(Customer customer, int amount) {
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		return customerRepo.save(customer);
	}
	
	
	
	

}
