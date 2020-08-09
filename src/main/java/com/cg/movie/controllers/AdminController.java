package com.cg.movie.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.entities.Movie;
import com.cg.movie.services.IAdminService;
import com.cg.movie.services.IMovieService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IMovieService movieService;

	// get count of customers

	@GetMapping("/countOfCustomers")
	public ResponseEntity<Long> countOfCustomers() {
		return new ResponseEntity<Long>(adminService.countOfCustomers(), HttpStatus.OK);
	}

	// get count of customers

	@GetMapping("/countOfTheatres")
	public ResponseEntity<Long> countOfTheatres() {
		return new ResponseEntity<Long>(adminService.countOfTheatres(), HttpStatus.OK);
	}

	// get count of customers

	@GetMapping("/countOfMovies")
	public ResponseEntity<Long> countOfMovies() {
		return new ResponseEntity<Long>(adminService.countOfMovies(), HttpStatus.OK);
	}


	@PostMapping("/theatre/movie")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
	{
		Movie movie1=movieService.addMovie(movie);
		return new ResponseEntity<Movie>(movie1,HttpStatus.OK);
	}
	
	@DeleteMapping("/theatre/movie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable long movieId)
	{
		movieService.deleteById(movieId);
		return new ResponseEntity<String>("Movie Deleted",HttpStatus.OK);
	}
	
	@GetMapping("/theatre/getAllMovies")
	public ResponseEntity<Set<Movie>> getAllMovies()
	{
		Set<Movie> movieList= movieService.findAllMovie();
		return new ResponseEntity<Set<Movie>>(movieList,HttpStatus.OK);	
	}	
}
