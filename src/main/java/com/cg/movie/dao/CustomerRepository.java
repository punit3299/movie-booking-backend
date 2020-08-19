package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT customer.email FROM Customer customer")
	public List<String> getAllEmail();
	
	@Query("SELECT customer.customerContact FROM Customer customer")
	public List<Long> getAllContact();
	
	@Query("SELECT customer.customerPassword FROM Customer customer WHERE customer.email= ?1")
	public String getPassword(String email);
	
	@Query("SELECT customer FROM Customer customer WHERE customer.email = ?1")
	public Customer getCustomerByEmail(String email);
}
