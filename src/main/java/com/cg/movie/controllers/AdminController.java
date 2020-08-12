package com.cg.movie.controllers;

import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.cg.movie.entities.City;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Show;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.response.GenderResponse;
import com.cg.movie.response.SuccessMessage;
import com.cg.movie.services.IAdminService;
import com.cg.movie.services.ICityService;
import com.cg.movie.services.IMovieService;
import com.cg.movie.services.IScreenService;
import com.cg.movie.services.ISeatService;
import com.cg.movie.services.IShowService;
import com.cg.movie.services.ITheatreService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
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
	
	// get count of customers

	@GetMapping("/countOfCustomers")
	public ResponseEntity<Long> countOfCustomers() {
		return new ResponseEntity<Long>(adminService.countOfCustomers(), HttpStatus.OK);
	}

	// get count of theatres

	@GetMapping("/countOfTheatres")
	public ResponseEntity<Long> countOfTheatres() {
		return new ResponseEntity<Long>(adminService.countOfTheatres(), HttpStatus.OK);
	}

	// get count of movies

	@GetMapping("/countOfMovies")
	public ResponseEntity<Long> countOfMovies() {
		return new ResponseEntity<Long>(adminService.countOfMovies(), HttpStatus.OK);
	}

	@PostMapping(value = "/screen/{theatreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> addScreen(@RequestBody Screen screen, @PathVariable long theatreId) {
		screenService.addScreen(theatreId, screen);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Add Screen Request", "Screen Successfuly Added"),
				HttpStatus.CREATED);
	}

	@PostMapping("/theatre/movie")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		Movie movie1 = movieService.addMovie(movie);
		return new ResponseEntity<Movie>(movie1, HttpStatus.OK);
	}

	@DeleteMapping("/theatre/movie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable long movieId) {
		movieService.deleteMovieById(movieId);
		return new ResponseEntity<String>("Movie Deleted", HttpStatus.OK);
	}

	@GetMapping("/theatre/getAllMovies")
	public ResponseEntity<Set<Movie>> getAllMovies() {
		Set<Movie> movieList = movieService.findAllMovie();
		return new ResponseEntity<Set<Movie>>(movieList, HttpStatus.OK);
	}

	// top 3 theatres

	@GetMapping("/topThreeTheatres")
	public ResponseEntity<List<Theatre>> topThreeTheatres() {
		return new ResponseEntity<List<Theatre>>(adminService.topThreeTheatres(), HttpStatus.OK);
	}

	// top 3 movies

	@GetMapping("/topThreeMovies")
	public ResponseEntity<List<Movie>> topThreeMovies() {
		return new ResponseEntity<List<Movie>>(adminService.topThreeMovies(), HttpStatus.OK);
	}

	// today's Revenue

	@GetMapping("/todayRevenue")
	public ResponseEntity<Double> todayRevenue() {
		return new ResponseEntity<Double>(adminService.todayRevenue(), HttpStatus.OK);
	}

	// today's Booking

	@GetMapping("/todayBookingCount")
	public ResponseEntity<Integer> todayBookingCount() {
		return new ResponseEntity<Integer>(adminService.todayBookingCount(), HttpStatus.OK);
	}

	// genderwise Count

	@GetMapping("/genderwiseCount")
	public ResponseEntity<GenderResponse> genderwiseCount() {
		return new ResponseEntity<GenderResponse>(adminService.genderwiseCount(), HttpStatus.OK);
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

	@PutMapping("/theatre/edit/{theatreId}")
	public ResponseEntity<String> updateTheatre(@RequestBody Theatre theatre, @PathVariable long theatreId) {
		theatreService.updateTheatre(theatre);
		return new ResponseEntity<String>("Theatre updated successfully", HttpStatus.OK);
	}

	@PostMapping(value = "/city")
	public ResponseEntity<City> AddCity(@RequestBody City city) {
		City newCity = cityService.addCity(city);
		return new ResponseEntity<City>(newCity, HttpStatus.OK);
	}

	@GetMapping(value = "/city/list")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> city = cityService.viewAllCity();
		return new ResponseEntity<List<City>>(city, HttpStatus.OK);
	}

	@GetMapping(value = "/theatre/{city}")
	public ResponseEntity<List<Theatre>> getTheatreByCity(@PathVariable String city) {
		List<Theatre> theatre = cityService.getAllTheatreByCity(city);
		return new ResponseEntity<List<Theatre>>(theatre, HttpStatus.OK);
	}

	@PostMapping(value = "/theatre")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre theatre) {
		Theatre newTheatre = theatreService.addTheatre(theatre);
		return new ResponseEntity<Theatre>(newTheatre, HttpStatus.OK);
	}

	@DeleteMapping("/theatre/{theatreId}")
	public ResponseEntity<String> deleteTheatre(@PathVariable long theatreId) {
		Theatre theatre = theatreService.getTheatreById(theatreId);
		theatreService.deleteTheatre(theatre);
		return new ResponseEntity<String>("Theatre Deleted", HttpStatus.OK);
	}

	@GetMapping("/theatre/list")
	public ResponseEntity<List<Theatre>> getAllTheatre() {
		List<Theatre> theatre = theatreService.viewAllTheatre();
		return new ResponseEntity<List<Theatre>>(theatre, HttpStatus.OK);
	}

	@PostMapping("/theatre/screen/show")
	public ResponseEntity<Long> addNewShow(@PathParam("theatreId") long theatreId, @PathParam("screenId") long screenId,
			@PathParam("movieId") long movieId, @RequestBody Show show) {
		return new ResponseEntity<Long>(showService.addNewShow(theatreId, screenId, movieId, show), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/theatre/screen/{showId}")
	public ResponseEntity<String> deleteShow(@PathVariable long showId) {
		showService.deleteShowById(showId);
		return new ResponseEntity<String>("Show Deleted", HttpStatus.OK);
	}
	
	@GetMapping("/theatre/screen/movie/show")
	public ResponseEntity<Set<Show>> getAllShows() {
		return new ResponseEntity<Set<Show>>(showService.getAllShow(), HttpStatus.OK);
	}


}
