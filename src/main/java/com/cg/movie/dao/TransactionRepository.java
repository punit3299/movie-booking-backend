package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
