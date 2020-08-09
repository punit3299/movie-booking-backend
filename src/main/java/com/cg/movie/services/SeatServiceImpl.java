package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.SeatRepository;
import com.cg.movie.entities.Seat;
@Service
public class SeatServiceImpl implements ISeatService {

	@Autowired
	SeatRepository seatRepo;
	@Override
	public Boolean chooseSeat(Seat seat) {
		
		seat.setSeatStatus("booked");
		seatRepo.save(seat);
		return true;
	}

}
