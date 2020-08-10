package com.cg.movie.services;

import java.util.List;

import com.cg.movie.entities.Seat;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;

public interface ISeatService {
	public BookedDetailsOfTicket bookSeat(BookTicketDetails bookSeatDetails);

}
