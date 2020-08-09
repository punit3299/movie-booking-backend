package com.cg.movie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.entities.Screen;
import com.cg.movie.response.SuccessMessage;
import com.cg.movie.services.IAdminService;
import com.cg.movie.services.IScreenService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService adminService;

	@Autowired
	IScreenService screenService;
	
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
	
	@PostMapping(value="/screen/{theatreId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessMessage> addScreen(@RequestBody Screen screen,@PathVariable long theatreId)
	{
		screenService.addScreen(theatreId, screen);
		return new ResponseEntity<SuccessMessage>(new SuccessMessage("Add Screen Request","Screen Successfuly Added"),HttpStatus.CREATED);		
	}
	
	@GetMapping(value="/screen",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Screen>> getAllScreen()
	{
		List<Screen> screens=screenService.getAllScreen();
		return new ResponseEntity<List<Screen>>(screens,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/screen/{screenId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Screen>> deleteScreenById(@PathVariable long screenId)
	{
		screenService.deleteScreen(screenId);
		List<Screen> screens=screenService.getAllScreen();
		return new ResponseEntity<List<Screen>>(screens,HttpStatus.OK);
	}
	
	@PostMapping(value="/seat",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> addSeatsInScreen(@RequestBody Screen screen)
	{
		Integer updatedNoOfSeats=screenService.addSeats(screen.getScreenId(), screen.getNoOfSeats());
		return new ResponseEntity<Integer>(updatedNoOfSeats,HttpStatus.ACCEPTED);
		
		
	}
	
	
}
