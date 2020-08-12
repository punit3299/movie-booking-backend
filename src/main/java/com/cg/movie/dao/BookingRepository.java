package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = "SELECT sum(b.totalCost) FROM Booking b where to_char(b.bookingDate,'dd-mm-yy')='17-05-20'", nativeQuery = true)
	Double todayRevenue();

	@Query(value = "SELECT count(*) FROM Booking b where to_char(b.bookingDate,'dd-mm-yy')='17-05-20'", nativeQuery = true)
	Integer todayBookingCount();
	
	
	@Query(value="SELECT * FROM booking_table WHERE ticket_id IN (SELECT ticket_id FROM ticket_table WHERE customer_id=?1) ", nativeQuery=true)
	public List<Booking> findByCustomerId(Long id);
	
	/*
	 * Query to Fetch Booking details using ticketId
	 */
	@Query("Select booking from Booking booking where booking.ticket.ticketId=?1")
	Booking getBooking(long ticketId);
	
	/*
	 * Query to Fetch Show details using ticketId
	 */
	@Query("Select booking.show.showId from Booking booking where booking.ticket.ticketId=?1")
	long getShowId(long ticketId);


}



