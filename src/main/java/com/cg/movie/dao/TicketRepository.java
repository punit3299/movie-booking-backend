package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	

	


}
