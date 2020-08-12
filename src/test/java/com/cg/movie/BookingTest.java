package com.cg.movie;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.services.BookingServiceImpl;

@SpringBootTest
class BookingTest {

	@Autowired
	BookingServiceImpl bookingService;
	
	@MockBean
	BookingRepository bookingRepo;

	@Test
	public void addBookingTest() {
		Booking booking=new Booking(new Long(1), Timestamp.from(Instant.now()) ,250.00);
		when(bookingRepo.save(booking)).thenReturn(booking);
		assertEquals(booking, bookingService.addBooking(booking));
	}
	
	@Test 
	public void getBookingTest() {
		Booking booking=new Booking(new Long(1), Timestamp.from(Instant.now()) ,250.00);
		when(bookingRepo.getOne(booking.getBookingId())).thenReturn(booking);
		assertEquals(booking, bookingService.getBooking(booking.getBookingId()));
	}

	@Test
	public void getPreviousBookingTest() {
		List<Booking> bookings=Stream.of(new Booking(new Long(108), Timestamp.from(Instant.now()) ,257.06), new Booking(new Long(109), Timestamp.from(Instant.now()) ,257.06)).collect(Collectors.toList());
	    when(bookingRepo.findByCustomerId(new Long(1190))).thenReturn(bookings);
	    assertEquals(2,bookingService.getPreviousBookings(new Long(1190)).size());
	}
	
	
}

