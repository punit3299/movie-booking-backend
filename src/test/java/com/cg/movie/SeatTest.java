package com.cg.movie;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.SeatRepository;
import com.cg.movie.entities.Seat;
import com.cg.movie.services.SeatServiceImpl;
@SpringBootTest
class SeatTest {

	@Autowired
	SeatServiceImpl seatService;
	
	@MockBean
	SeatRepository seatRepo;
	 
	@Test
	public void chooseSeatTest()
	{
		Seat seat=new Seat(new Long(123),"vacant",210.50);
		when(seatRepo.save(seat)).thenReturn( seat);
		assertEquals(true,seatService.chooseSeat(seat));
	}

}
