package com.cg.movie.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.BookingRepository;
import com.cg.movie.dao.CityRepository;
import com.cg.movie.dao.CustomerRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.SeatRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.dao.TicketRepository;
import com.cg.movie.dao.TransactionRepository;
import com.cg.movie.entities.Booking;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Seat;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.entities.Ticket;
import com.cg.movie.entities.Transaction;
import com.cg.movie.exception.SeatAlreadyBookedException;
import com.cg.movie.response.BookTicketDetails;
import com.cg.movie.response.BookedDetailsOfTicket;
@Service
public class SeatServiceImpl implements ISeatService {

	@Autowired
	SeatRepository seatRepo;
	@Autowired
	CityRepository cityRepo;
	@Autowired
	ScreenRepository screenRepo;
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	ShowRepository showRepo;
	@Autowired
	TheatreRepository theatreRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	TicketRepository ticketRepo;
	@Autowired
	IScreenService screenService;
	
	@Autowired
	IShowService showService;
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	ITicketService ticketService;
	
	@Autowired
	ICityService cityService;
	
	@Autowired
	ITheatreService theatreService;
	
	@Autowired
	IMovieService movieService;
	
	@Autowired
	TransactionRepository transactionRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	/********************************************************************************
	 * 
	 * Method : bookSeat
	 * 
	 * Description: this method will check for seat availability and then book the seat for customer
	 * 
	 * @return : BookedDetailsOfTicket
	 * 
	 *         Created by: Raman  ,10 August 2020
	 * 
	 **********************************************************************************/
	
	@Override
	public BookedDetailsOfTicket bookSeat(BookTicketDetails details) {
		
		customerService.findCustomerById(details.getCustomerId());
		screenService.findScreenById(details.getScreenId());
		showService.findShowById(details.getShowId());
		movieService.findMovieById(details.getMovieId());
		theatreService.getTheatreById(details.getTheatreId());
		cityService.searchCity(details.getCityName());
		
		Screen screen=screenRepo.findById(details.getScreenId()).get();
		
		Customer customer=customerRepo.findById(details.getCustomerId()).get();
		
		Movie movie=movieRepo.findById(details.getMovieId()).get();
		
		Show show= showRepo.findById(details.getShowId()).get();
		
		Theatre theatre=theatreRepo.findById(details.getTheatreId()).get();


		
		if(seatRepo.findSeatByShowId(show.getShowId())==null) {
			Seat seat=new Seat();
			seat.setSeatNumber(details.getSeatNo());
			seat.setSeatPrice(details.getTicketPrice());
			seat.setShow(show);
			seatRepo.save(seat);
			show.addSeat(seat);
		}
		else {
			Seat seat=seatRepo.findSeatByShowId(show.getShowId());
			List<String> bookedSeatNumbers=new ArrayList<>();
			
			for(int i=0;i<seat.getSeatNumber().length();i++)
			{
				String temp="";
				if(seat.getSeatNumber().charAt(i)==',')
				{
					continue;
				}
				else
				{
					while(seat.getSeatNumber().charAt(i)!=',')
					{
						temp+=seat.getSeatNumber().charAt(i);
						i++;
					}
					bookedSeatNumbers.add(temp);
				}
			}
			
			System.out.println(bookedSeatNumbers);
			
			
			
			
			List<String> seatNumbers=new ArrayList<>();
			String seats=details.getSeatNo();
			for(int i=0;i<seats.length();i++)
			{
				String temp="";
				if(seats.charAt(i)==',')
				{
					continue;
				}
				else
				{
					while(seats.charAt(i)!=',')
					{
						temp+=seats.charAt(i);
						i++;
					}
					seatNumbers.add(temp);
				}
			}
			
			System.out.println(seatNumbers);
			
			
			for(String bookedNumber:bookedSeatNumbers)
			{
			for(String seatChecking:seatNumbers)
			{  
				if(bookedNumber.equals(seatChecking))
				{
					logger.error("Seats already booked");
					throw new SeatAlreadyBookedException("Seats Already Booked");
				}
				
			}
			}
			seat.setSeatNumber(seat.getSeatNumber()+details.getSeatNo());
			seatRepo.save(seat);
			
			
		}
		
		Ticket ticket= new Ticket();
		
		ticket.setScreenName(screen.getScreenName());
		ticket.setSeatName(details.getSeatNo());
		ticket.setTicketStatus(true);
		ticket.setCustomer(customer);
		Ticket bookedTicket=ticketRepo.save(ticket);
		customer.addTicket(bookedTicket);
		
		Transaction transaction=new Transaction();
		transaction.setShow(show);
		transaction.setTransactionMessage("Movie "+ movie.getMovieName()+" booked, Price Rs. "+details.getTicketPrice());
		transaction.setTransactionTime(Timestamp.from(Instant.now()));
		Transaction bookedTransaction=transactionRepo.save(transaction);
		
		Booking booking= new Booking();
		booking.setBookingDate(details.getBookingDate());
		booking.setMovie(movie.getMovieName());
		booking.setStatus(true);
		booking.setTotalCost(details.getTicketPrice());
		booking.setShow(show);
		booking.setTicket(bookedTicket);
		booking.setTransaction(bookedTransaction);
		bookingRepo.save(booking);
		
		BookedDetailsOfTicket bookedDeatilsOfTicket=new BookedDetailsOfTicket();
		bookedDeatilsOfTicket.setBookingId(booking.getBookingId());
		bookedDeatilsOfTicket.setBookingDate(details.getBookingDate());
		bookedDeatilsOfTicket.setCityName(details.getCityName());
		bookedDeatilsOfTicket.setMovieName(movie.getMovieName());
		bookedDeatilsOfTicket.setScreenName(screen.getScreenName());
		bookedDeatilsOfTicket.setSeatNo(details.getSeatNo());
		bookedDeatilsOfTicket.setShowDate(details.getShowDate());
		bookedDeatilsOfTicket.setTheatreName(theatre.getTheatreName());
		bookedDeatilsOfTicket.setTotalCost(details.getTicketPrice());
		return bookedDeatilsOfTicket;	
		}

	/********************************************************************************
	 * 
	 * Method : BookedSeatInShow
	 * 
	 * Description: this method will give the booked seat number of particular show
	 * 
	 * @return : array of seat number
	 * 
	 *         Created by: Raman  ,10 August 2020
	 * 
	 **********************************************************************************/
	
	public int[] BookedSeatInShow(Long showId) {
		System.out.println("service showId= "+showId);
		Seat seat=seatRepo.findSeatByShowId(showId);
		int bookedSeatNo[];
		int arr[] = new int[0];

		if(seat!=null)
		{
		String bookedSeat=seat.getSeatNumber();
		String[] bookedSeatArray;
		bookedSeatArray=bookedSeat.split(",");
		for(int i=0;i<bookedSeatArray.length;i++)
		{
			System.out.print("string Seat Array= "+bookedSeatArray[i]);
		}
		
		
		
		bookedSeatNo=new int[bookedSeatArray.length];
		for(int i=0;i<bookedSeatArray.length;i++)
		{ 
			bookedSeatNo[i]=Integer.parseInt(bookedSeatArray[i]);
		}
		for(int i=0;i<bookedSeatNo.length;i++)
		{
			System.out.print("string Seat Array= "+bookedSeatNo[i]);
		}
		return bookedSeatNo;
		}
		return arr;
	}
	
	
	

}
