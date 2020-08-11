package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT COUNT(*) FROM Seat seat WHERE seat.show.showId = ?1 ")
	int ifExistSeatOfShowId(long showId);
	
	@Query("SELECT seat FROM Seat seat WHERE seat.show.showId = ?1 ")
	Seat findSeatByShowId(long showId);
	
	
	@Query("select seat from Seat seat where seat.show.showId=?1")
	Seat getSeat(long showId);

}
