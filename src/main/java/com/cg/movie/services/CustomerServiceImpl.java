package com.cg.movie.services;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	ShowRepository showRepo;
	
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
		
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		
		return customerRepo.save(customer);
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
	public Customer refundMoneyToWallet(Customer customer,long showId, int amount) {
		
		customer.setCustomerBalance(customer.getCustomerBalance()+amount);
		
		Show show=showRepo.findById(showId).get();
		
		Transaction transaction = new Transaction();
		transaction.setTransactionMessage("Rs. "+amount+" refunded to Wallet regarding show: "+show.getShowName());
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		transaction.setShow(show);
		
		transactionRepo.save(transaction);
		
		return customerRepo.save(customer);
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

	
	
	
	

}
