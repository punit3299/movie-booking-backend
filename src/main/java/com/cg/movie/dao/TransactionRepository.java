package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	
	@Query("Select transaction from Transaction transaction WHERE transaction.customer.customerId=?1")
	List<Transaction> getAllTransactions(long customerId);

}
