package com.cg.movie;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.services.CustomerServiceImpl;

@SpringBootTest
class CustomerTest {

	@Autowired
	CustomerServiceImpl customerService;
	
	@MockBean
	CustomerRepository customerRepo;
	
	
	@Test
	public void addMoneyToWalletTest() {
		Customer customer=new Customer(new Long(1), "Siddharth ", "Haha", 7973657728L,"Male", 0);
		customerService.addMoneyToWallet(customer, 500);
		verify(customerRepo,times(1)).save(customer);
		
	}
	
	
	  @Test public void refundMoneyToWalletTest() { 
		  Customer customer=new Customer(new Long(1), "Siddharth ", "Haha", 7973657728L,"Male", 0);
			customerService.refundMoneyToWallet(customer, 500);
			verify(customerRepo,times(1)).save(customer);
	  
	  }

}
