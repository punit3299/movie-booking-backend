package com.cg.movie.services;


import java.sql.Timestamp;
import java.time.Instant;

import java.util.Optional;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.AdminRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.CustomerNotFoundException;
import com.cg.movie.exception.InvalidAttributeException;
import com.cg.movie.response.LoginCredential;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private ShowRepository showRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/********************************************************************************
	 * 
	 * Method : addCustomer
	 * 
	 * Description: for adding the Customer.
	 * 
	 * @param  : Customer object
	 * 
	 * @return : Customer Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,9 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	/********************************************************************************
	 * 
	 * Method : findCustomerById
	 * 
	 * Description: for finding customer by Id
	 * 
	 * @param  : customerId		Customer customerId
	 * 
	 * @return : Customer Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,9 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Customer findCustomerById(long customerId) {
		
		boolean checkCustomer= customerRepo.existsById(customerId);
		
		if(checkCustomer==false)
		{
			logger.error("Customer not found with customerId: "+customerId);
			throw new CustomerNotFoundException("Customer Not Found");
		}
		else {
			Customer customer=customerRepo.getOne(customerId);
			logger.info(" Customer found with customerId:"+customerId);
			return customer;
		}
		
	}
	
	/********************************************************************************
	 * 
	 * Method : addMoneyToWallet
	 * 
	 * Description: for adding money to wallet
	 * 
	 * @param  : customer object, amount	
	 * 
	 * @return : Customer Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,9 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Customer addMoneyToWallet(Customer customer, int amount) {
		
		Customer newCustomer=findCustomerById(customer.getCustomerId());
		
		newCustomer.setCustomerBalance(newCustomer.getCustomerBalance()+amount);
		
		Transaction transaction=new Transaction();
		
		transaction.setCustomer(newCustomer);
		transaction.setTransactionMessage("Rs. "+ amount + " Added to Wallet");
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		transactionRepo.save(transaction);
		
		return customerRepo.save(newCustomer);
	}
	
	/********************************************************************************
	 * 
	 * Method : refundMoneyToWallet
	 * 
	 * Description: for refund money to wallet
	 * 
	 * @param  : customer obejct, showId, amount 		Show showId
	 * 
	 * @return : Customer Entity
	 * 
	 *         Created by: Siddharth Raghuvanshi ,9 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public Customer refundMoneyToWallet(Customer customer,long showId, double amount) {
		
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		
		Show show=showRepo.findById(showId).get();
		
		Transaction transaction = new Transaction();
		transaction.setTransactionMessage("Rs. "+amount+" refunded to Wallet regarding Movie: "+show.getMovie().getMovieName());
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		transaction.setCustomer(customer);
		transaction.setShow(show);
		
		transactionRepo.save(transaction);
		
		return customerRepo.save(customer);
	}

	@Override
	public double getBalance(Long customerId) {
		if(customerRepo.existsById(customerId))
		{
			Customer customer=customerRepo.findById(customerId).get();
			return customer.getCustomerBalance();
		}
		else
		{
			throw new CustomerNotFoundException("Customer not Exist");
		}
	}
	/********************************************************************************
	 * 
	 * Method : getAllCustomers
	 * 
	 * Description: Return list of customer details
	 * 
	 * @return : List<Customer> 
	 * 
	 *         Created by: Saurav Suman ,12 August 2020
	 * 
	 **********************************************************************************/
	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customerList=customerRepo.findAll();
		return customerList;
	}

	@Override
	public boolean findEmailIfExists(String email) {
		List<String> customerEmails= customerRepo.getAllEmail().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());;
		if(customerEmails.contains(email.toLowerCase()))
		{
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean findContactNoIfExists(long contactNumber) {
		List<Long> customerContacts=customerRepo.getAllContact();
		if(customerContacts.contains(contactNumber))
			return true;
		else
		return false;
	}

	@Override
	public String validateCredential(LoginCredential credentials) {
		if(adminService.findEmailIfExists(credentials.getEmail()) )
		{
			if(adminRepo.getPassword(credentials.getEmail()).equals(credentials.getPassword()))
				return "admin";
		}
		else if(findEmailIfExists(credentials.getEmail()))
		{
			if(customerRepo.getPassword(credentials.getEmail()).equals(credentials.getPassword()))
				return "customer";
		}
		
			throw new InvalidAttributeException("Username or Password Invalid");
		
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		if(findEmailIfExists(email))
		{
			Customer customer=customerRepo.getCustomerByEmail(email);
			return customer;
		}
		else
			throw new CustomerNotFoundException("Customer not found");
	}


}
