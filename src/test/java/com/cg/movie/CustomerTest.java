package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.entities.Customer;
import com.cg.movie.services.ICustomerService;

@SpringBootTest
class CustomerTest {

	@Autowired
	ICustomerService iCustomerService;
	
	@MockBean
	CustomerRepository customerRepo;
	
	
	@Test
	public void addMoneyToWalletTest() {
		Customer customer=new Customer(new Long(1), "Siddharth ", "Haha", 7973657728L,"Male", 0);
		when(customerRepo.save(customer)).thenReturn(customer);
		assertEquals(customer, iCustomerService.addMoneyToWallet(customer, 500));
		
	}
	
	
	  @Test public void refundMoneyToWalletTest() { 
		  Customer customer=new Customer(new Long(1), "Siddharth ", "Haha", 7973657728L,"Male", 0);
		  when(customerRepo.save(customer)).thenReturn(customer);
		  assertEquals(customer, iCustomerService.refundMoneyToWallet(customer, 300));
	  
	  }

}
