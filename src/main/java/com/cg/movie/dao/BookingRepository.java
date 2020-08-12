package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query(value = "SELECT sum(total_cost) FROM booking_table where to_char(booking_date,'dd-mm-yy')='17-05-20'", nativeQuery = true)
	Double todayRevenue();

	@Query(value = "SELECT count(*) FROM booking_table where to_char(booking_date,'dd-mm-yy')='17-05-20'", nativeQuery = true)
	Integer todayBookingCount();
	
	
	@Query(value="Select booking from Booking booking where booking.customer.customerId=?1", nativeQuery=true)
	public List<Booking> findByCustomerId(Long id);


}
