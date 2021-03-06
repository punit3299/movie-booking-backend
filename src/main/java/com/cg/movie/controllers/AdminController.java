package com.cg.movie.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.entities.Booking;
import com.cg.movie.entities.City;
import com.cg.movie.entities.Customer;
import com.cg.movie.entities.Language;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.request.ShowRequestVO;
import com.cg.movie.response.GenderResponse;
import com.cg.movie.response.GenreResponse;
import com.cg.movie.response.MovieResponseVO;
import com.cg.movie.response.ShowResponseVO;
import com.cg.movie.response.SuccessMessage;
import com.cg.movie.services.IAdminService;
import com.cg.movie.services.ICityService;
import com.cg.movie.services.ICustomerService;
import com.cg.movie.services.ILanguageService;
import com.cg.movie.services.IMovieService;
import com.cg.movie.services.IScreenService;
import com.cg.movie.services.ISeatService;
import com.cg.movie.services.IShowService;
import com.cg.movie.services.ITheatreService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@Validated
public class AdminController {

	@Autowired
	IAdminService adminService;

	@Autowired
	IMovieService movieService;

	@Autowired
	IScreenService screenService;

	@Autowired
	ITheatreService theatreService;

	@Autowired
	ICityService cityService;

	@Autowired
	ISeatService seatService;

	@Autowired
	IShowService showService;

	@Autowired
	ILanguageService languageService;
	
	@Autowired
	ICustomerService customerService;


	/**
	 * ---------------------------------------------------------------------
	 * Getting count of customers
	 */

	@GetMapping("/countOfCustomers")
	public ResponseEntity<Long> countOfCustomers() {
		return new ResponseEntity<Long>(adminService.countOfCustomers(), HttpStatus.OK);
	}

	/**
	 * Getting count of theatres
	 */

	@GetMapping("/countOfTheatres")
	public ResponseEntity<Long> countOfTheatres() {
		return new ResponseEntity<Long>(adminService.countOfTheatres(), HttpStatus.OK);
	}

	/**
	 * Getting count of movies
	 */

	@GetMapping("/countOfMovies")
	public ResponseEntity<Long> countOfMovies() {
		return new ResponseEntity<Long>(adminService.countOfMovies(), HttpStatus.OK);
	}

	/**
	 * Getting Top 3 Theatres
	 */

	@GetMapping("/topThreeTheatres")
	public ResponseEntity<List<Theatre>> topThreeTheatres() {
		return new ResponseEntity<List<Theatre>>(adminService.topThreeTheatres(), HttpStatus.OK);
	}

	/**
	 * Getting Top 3 Movies
	 */

	@GetMapping("/topThreeMovies")
	public ResponseEntity<List<Movie>> topThreeMovies() {
		return new ResponseEntity<List<Movie>>(adminService.topThreeMovies(), HttpStatus.OK);
	}
	
	/**
	 * Getting Recent 3 Bookings
	 */
	
	@GetMapping("/recentThreeBookings")
	public ResponseEntity<List<Booking>> getRecentThreeBookings() {
		return new ResponseEntity<List<Booking>>(adminService.getRecentThreeBookings(), HttpStatus.OK);
	}

	/**
	 * Getting Today's Revenue
	 */

	@GetMapping("/todayRevenue")
	public ResponseEntity<Double> todayRevenue() {
		return new ResponseEntity<Double>(adminService.todayRevenue(), HttpStatus.OK);
	}

	/**
	 * Getting Today's Bookings count
	 */

	@GetMapping("/todayBookingCount")
	public ResponseEntity<Integer> todayBookingCount() {
		return new ResponseEntity<Integer>(adminService.todayBookingCount(), HttpStatus.OK);
	}

	/**
	 * Getting gender-wise count of customers
	 */
	
	@GetMapping("/genderwiseCount")
	public ResponseEntity<GenderResponse> genderwiseCount() {
		return new ResponseEntity<GenderResponse>(adminService.genderwiseCount(), HttpStatus.OK);
	}
	
	/**
	 * Getting genre-wise movies count 
	 */
	
	@GetMapping("/genrewiseMoviesCount")
	public ResponseEntity<List<GenreResponse>> genrewiseMoviesCount() {
		return new ResponseEntity<List<GenreResponse>>(adminService.genrewiseMoviesCount(), HttpStatus.OK);
	}
	
	/**
	 * Getting Last 7 Days revenues 
	 */
	
	@GetMapping("/recentRevenues")
	public ResponseEntity<List<Double>> recentRevenues() {
		return new ResponseEntity<List<Double>>(adminService.recentRevenues(), HttpStatus.OK);
	}
	
	/**
	 * Getting Last 7 Days Bookings count
	 */
	
	@GetMapping("/recentBookingsCount")
	public ResponseEntity<List<Integer>> recentBookingsCount() {
		return new ResponseEntity<List<Integer>>(adminService.recentBookingsCount(), HttpStatus.OK);
	}
	
	/**
	 * Getting All Bookings
	 */
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> getBookings() {
		return new ResponseEntity<List<Booking>>(adminService.getBookings(), HttpStatus.OK);
	}
	
	/**
	 * Delete Booking By bookingId
	 */
	
	@GetMapping("/deleteBooking/{bookingId}")
	public ResponseEntity<SuccessMessage> deleteBookingById(@PathVariable Long bookingId) {
		return new ResponseEntity<SuccessMessage>(adminService.deleteBookingById(bookingId), HttpStatus.OK);
	}
	
	/**
	 * ------------------------------------------------------------
	 */
	
	@PostMapping(value = "/screen/{theatreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> addScreen(@RequestBody Screen screen, @PathVariable long theatreId) {
		System.out.println(screen.getScreenName()+" "+screen.getNoOfSeats());
		screenService.addScreen(theatreId, screen);
		
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Add Screen Request", "Screen Successfuly Added"),
				HttpStatus.CREATED);
	}

	@PostMapping("/movie")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) throws MoviesNotFoundException {
		Movie movie1 = movieService.addMovie(movie);
		return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
	}

	@DeleteMapping("/movie/{movieId}")
	public ResponseEntity<Set<MovieResponseVO>> deleteMovie(@PathVariable Long movieId) {
		movieService.deleteMovieById(movieId);
		Set<MovieResponseVO> movies=movieService.findAllMovie();
		return new ResponseEntity<Set<MovieResponseVO>>(movies, HttpStatus.OK);
	}

	@GetMapping("/movie/getAllMovies")
	public ResponseEntity<Set<MovieResponseVO>> getAllMovies() {
		Set<MovieResponseVO> movieList = movieService.findAllMovie();
		return new ResponseEntity<Set<MovieResponseVO>>(movieList, HttpStatus.OK);
	}

	@GetMapping(value = "/screen/{theatreId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Screen>> getAllScreen(@PathVariable long theatreId) {
		List<Screen> screens = screenService.getAllScreen(theatreId);
		return new ResponseEntity<List<Screen>>(screens, HttpStatus.OK);
	}

	@DeleteMapping(value = "/screen/{theatreId}/{screenId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Screen>> deleteScreenById(@PathVariable long screenId, @PathVariable long theatreId)
			throws ScreenNotFoundException {
		screenService.deleteScreen(screenId);
		List<Screen> screens = screenService.getAllScreen(theatreId);
		return new ResponseEntity<List<Screen>>(screens, HttpStatus.OK);
	}

	@PostMapping(value = "/seat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> addSeatsInScreen(@RequestBody Screen screen) throws ScreenNotFoundException {
		Integer updatedNoOfSeats = screenService.addSeats(screen.getScreenId(), screen.getNoOfSeats());
		return new ResponseEntity<Integer>(updatedNoOfSeats, HttpStatus.ACCEPTED);

	}

	@GetMapping(value="customers",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		List<Customer> customersList=customerService.getAllCustomer();
		return new ResponseEntity<List<Customer>>(customersList,HttpStatus.OK);
	}
	
	@PatchMapping(value = "/seat", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> updateSeatsInScreen(@RequestBody Screen screen) throws ScreenNotFoundException {
		Integer updatedNoOfSeats = screenService.updateNoOfSeats(screen.getScreenId(), screen.getNoOfSeats());
		return new ResponseEntity<Integer>(updatedNoOfSeats, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/seat/{screenId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getNoOfSeat(@PathVariable long screenId) throws ScreenNotFoundException {
		Integer noOfSeat = screenService.getNoOfSeats(screenId);
		return new ResponseEntity<Integer>(noOfSeat, HttpStatus.ACCEPTED);
	}

	/*
	 * Update Theatre
	 */

	@PutMapping("/theatre/edit/{theatreId}")
	public ResponseEntity<Theatre> updateTheatre(@RequestBody Theatre theatre, @PathVariable long theatreId) {
		theatreService.updateTheatre(theatre);
		return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
	}

	/*
	 * Add city
	 */

	@PostMapping(value = "/city")
	public ResponseEntity<City> AddCity(@RequestBody City city) {
		City newCity = cityService.addCity(city);
		return new ResponseEntity<City>(newCity, HttpStatus.OK);
	}

	/*
	 * View All cities
	 */

	@GetMapping(value = "/city/list")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> city = cityService.viewAllCity();
		return new ResponseEntity<List<City>>(city, HttpStatus.OK);
	}

	/*
	 * get All Theatres in particular city
	 */

	@GetMapping(value = "/theatre/{city}")
	public ResponseEntity<List<Theatre>> getTheatreByCity(@PathVariable String city) {
		List<Theatre> theatre = cityService.getAllTheatreByCity(city);
		return new ResponseEntity<List<Theatre>>(theatre, HttpStatus.OK);
	}

	/*
	 * Add Theatre
	 */

	@PostMapping(value = "/theatre")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
		Theatre newTheatre = theatreService.addTheatre(theatre);
		return new ResponseEntity<Theatre>(newTheatre, HttpStatus.OK);
	}

	/*
	 * Delete Theatre
	 */

	@DeleteMapping("/theatre/{theatreId}")
	public ResponseEntity<Theatre> deleteTheatre(@PathVariable long theatreId) {
		Theatre theatre = theatreService.getTheatreById(theatreId);
		theatreService.deleteTheatre(theatre);
		return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
	}

	/*********************************
	 *
	 * Get All Theatre
	 * 
	 *********************************/

	@GetMapping("/theatre/list")
	public ResponseEntity<List<Theatre>> getAllTheatre() {
		List<Theatre> theatre = theatreService.viewAllTheatre();
		return new ResponseEntity<List<Theatre>>(theatre, HttpStatus.OK);
	}
	
	/*
	 * Get Theatre By Id
	 */
	@GetMapping("/getTheatre/{theatreId}")
	public ResponseEntity<Theatre> getTheatreById(@PathVariable long theatreId)
	{
		return new ResponseEntity<Theatre>(theatreService.getTheatreById(theatreId),HttpStatus.OK);
	}
	
	@PostMapping("/theatre/screen/show")
	public ResponseEntity<Long> addNewShow(@Valid @RequestBody ShowRequestVO showRequestVO) {
		return new ResponseEntity<Long>(showService.addNewShow(showRequestVO), HttpStatus.CREATED);
	}

	@DeleteMapping("/theatre/screen/{showId}")
	public ResponseEntity<String> deleteShowById(@PathVariable long showId) {
		showService.deleteShowById(showId);
		return new ResponseEntity<String>("Show Deleted", HttpStatus.OK);
	}

	@GetMapping("/{theatreId}/{screenId}/show")
	public ResponseEntity<Set<ShowResponseVO>> getAllShowsByTheatreId(@PathVariable("theatreId") Long theatreId,
			@PathVariable("screenId") Long screenId) {
		return new ResponseEntity<Set<ShowResponseVO>>(showService.getAllShow(theatreId,screenId), HttpStatus.OK);
	}

	@PostMapping("/{movieId}/language")
	public ResponseEntity<Language> addLanguages(@PathVariable("movieId") long movieId,
			@RequestBody Language language) {
		return new ResponseEntity<Language>(languageService.addLanguage(language, movieId), HttpStatus.CREATED);
	}
	
	@GetMapping("/city/{cityId}")
	public ResponseEntity<City> getCityById(@PathVariable long cityId)
	{
		return new ResponseEntity<City>(cityService.getCityById(cityId), HttpStatus.OK);
	}
}
