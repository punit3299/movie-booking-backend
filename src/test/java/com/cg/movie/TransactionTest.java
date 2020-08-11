package com.cg.movie;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Transaction;
import com.cg.movie.services.ITransactionService;

@SpringBootTest
class TransactionTest {

	@Autowired
	ITransactionService iTransactionService;

	@MockBean
	TransactionRepository transactionRepo;
	
	/*
	 * Saving Transactions Test-Case
	 */
	@Test
	public void addTranactionTest() {
		Transaction transaction=new Transaction(new Long(1),"Added Money To Wallet",Timestamp.from(Instant.now()));
		when(transactionRepo.save(transaction)).thenReturn(transaction);
		assertEquals(transaction,iTransactionService.addTransaction(transaction));
	}
}
