package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.ScreenRepository;
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Screen;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.InvalidAttributeException;
import com.cg.movie.exception.ScreenNotFoundException;
import com.cg.movie.exception.TheatreNotFoundException;
import com.cg.movie.services.IScreenService;

@SpringBootTest
public class ScreenTest {

	@Autowired
	IScreenService screenService;

	@MockBean
	ScreenRepository screenRepo;

	@MockBean
	TheatreRepository theatreRepo;

	private static Theatre theatre;
	private static Screen screen1, screen2, screen3;

	/*
	 * Creating objects which will get mocked
	 */

	@BeforeEach
	public void init() {
		theatre = new Theatre((long) 1, "Xion", 5, "Mohit", 7973657728L);
		screen1 = new Screen((long) 101, "Audi1", 75, true);
		screen2 = new Screen((long) 102, "Audi 2", 80, true);
		screen3 = new Screen((long) 103, "Audi 3", 70, true);
	}

	/*
	 * Add Screen Test Case
	 */
	@Test
	@DisplayName("Test for adding screen in theatre")
	public void addScreenTest() {

		/*
		 * Valid theatreId
		 */
		when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(true);
		when(theatreRepo.findById(theatre.getTheatreId())).thenReturn(Optional.of(theatre));
		when(screenRepo.save(screen1)).thenReturn(screen1);
		assertEquals(screen1, screenService.addScreen(theatre.getTheatreId(), screen1));

		/*
		 * Invalid theatreId
		 */
		when(theatreRepo.existsById((long) 101)).thenReturn(false);
		assertThrows(TheatreNotFoundException.class, () -> {
			screenService.addScreen((long) 101, screen1);
		});

	}

	/*
	 * Fetch All Screen Test-Case
	 */
	@Test
	@DisplayName("Test for fetching all screen details of particular theatre")
	public void getAllScreenTest() {
		
		List<Screen> screenList = new ArrayList<>();
		screenList.add(screen1);
		screenList.add(screen2);
		screenList.add(screen3);
		

		/*
		 * For valid theatreId
		 */
		when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(true);
		when(screenRepo.findAll(theatre.getTheatreId())).thenReturn(screenList);
		assertEquals(3, screenService.getAllScreen(theatre.getTheatreId()).size());

		/*
		 * For invalid theatreId
		 */
		when(theatreRepo.existsById((long) 101)).thenReturn(false);
		assertThrows(TheatreNotFoundException.class, () -> {
			screenService.getAllScreen((long) 101);
		});

	}


	/*
	 * Deleting Screen Of Particular Theatre Test-Case
	 */
	@Test
	@DisplayName("Test for deleting screen details of particular theatre")
	public void deleteScreenTest() throws ScreenNotFoundException {

		/*
		 * For valid screenId
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		screenService.deleteScreen((long) 101);
		verify(screenRepo, times(1)).deleteScreenById((long) 101);

		/*
		 * For invalid screenId
		 */
		when(screenRepo.existsById((long) 110)).thenReturn(false);
		assertThrows(ScreenNotFoundException.class, () -> {
			screenService.deleteScreen((long) 110);
		});
	}

	/*
	 * Add Seat In Particular Screen Test-Case
	 */
	@Test
	@DisplayName("Test for adding seats in particular screen")
	public void addSeatTest() throws ScreenNotFoundException, InvalidAttributeException {
		
		/*
		 * For valid screenId
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		when(screenRepo.findById(screen1.getScreenId())).thenReturn(Optional.of(screen1));
		assertEquals(75, screenService.addSeats(screen1.getScreenId(), 75));

		/*
		 * For zero or less than seats
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		when(screenRepo.findById(screen1.getScreenId())).thenReturn(Optional.of(screen1));
		assertThrows(InvalidAttributeException.class, () -> {
			screenService.addSeats(screen1.getScreenId(), 0);
		});

		/*
		 * For invalid screenId
		 */
		when(screenRepo.existsById((long) 111)).thenReturn(false);
		assertThrows(ScreenNotFoundException.class, () -> {
			screenService.addSeats(111, 75);
		});

	}

	
	/*
	 * Update Seat In Particular Screen Test-Case
	 */
	@Test
	@DisplayName("Test for updating seats in particular screen")
	public void updateSeatTest() throws ScreenNotFoundException {
		
		/*
		 * For valid screenId
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		when(screenRepo.findById(screen1.getScreenId())).thenReturn(Optional.of(screen1));
		assertEquals(150, screenService.updateNoOfSeats(screen1.getScreenId(), 75));

		/*
		 * For zero or less than seats
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		when(screenRepo.findById(screen1.getScreenId())).thenReturn(Optional.of(screen1));
		assertThrows(InvalidAttributeException.class, () -> {
			screenService.updateNoOfSeats(screen1.getScreenId(), 0);
		});

		/*
		 * For invalid screenId
		 */
		when(screenRepo.existsById((long) 111)).thenReturn(false);
		assertThrows(ScreenNotFoundException.class, () -> {
			screenService.updateNoOfSeats(111, 75);
		});

	}
	/*
	 * Fetch No Of Seats In Particular Screen Test-Case
	 */
	@Test
	@DisplayName("Test for fetching no of seats in particular screen")
	public void getNoOfSeatTest() throws ScreenNotFoundException {
		/*
		 * For valid screenId
		 */
		when(screenRepo.existsById(screen1.getScreenId())).thenReturn(true);
		when(screenRepo.findById(screen1.getScreenId())).thenReturn(Optional.of(screen1));
		assertEquals(75, screenService.getNoOfSeats(screen1.getScreenId()));

		/*
		 * For invalid screenId
		 */
		when(screenRepo.existsById((long) 111)).thenReturn(false);
		assertThrows(ScreenNotFoundException.class, () -> {
			screenService.getNoOfSeats(111);
		});

	}

}