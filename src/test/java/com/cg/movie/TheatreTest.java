package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.TheatreNotFoundException;
import com.cg.movie.services.ITheatreService;
import com.cg.movie.services.TheatreServiceImpl;

@SpringBootTest
class TheatreTest {

	@Autowired
	ITheatreService theatreService;

	@MockBean
	TheatreRepository theatreRepo;

	
	/*
	 *  Add Theatre Test case
	 */

	@Test
	public void addTheatreTest() 
	{
		Theatre theatre = new Theatre(1L, "Xion", 5, "Mohit", 7973657728L);
		when(theatreRepo.save(theatre)).thenReturn(theatre);
		assertEquals(theatre, theatreService.addTheatre(theatre));
	}
	
	/*
	 * Delete Theatre Test case
	 */
	
	  @Test 
	  public void deleteTheatreTest()
	  { 
		  Theatre theatre = new Theatre(1L, "Xion", 5, "Mohit", 7973657728L);
		  when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(true);
		  theatreService.deleteTheatre(theatre);
		  verify(theatreRepo,times(1)).delete(theatre); 
	  }
	  
	  /*
	   * Delete Theatre Exception Test case
	   */
	  
	  @Test
	  public void theatreNotFoundExceptiondeleteTest()
	  {
		  Theatre theatre = new Theatre(6L, "Xion", 5, "Mohit", 7973657728L);
		  when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(false);
		  assertThrows(TheatreNotFoundException.class, ()->{theatreService.deleteTheatre(theatre);});
	  }
	  
	  /*
	   * Get all Theatre Exception test case
	   */
	  
	  @Test
	  public void theatreNotFoundExceptionViewAllTest()
	  {
		  List<Theatre> theatre= new ArrayList<Theatre>();
		  when(theatreRepo.findAll()).thenReturn(theatre);
		  assertThrows(TheatreNotFoundException.class, ()->{theatreService.viewAllTheatre();});  
	  }
	  
	  /*
	   * Get all theatre test case
	   */
	  
		@Test
		public void viewAllTheaterTest() 
		{
			Theatre theatreXion = new Theatre(1L, "Xion", 5, "Mohit", 7973657728L);
			Theatre theatreViva = new Theatre(2L, "Viva", 4, "Rohit", 7973658828L);
			when(theatreRepo.findAll()).thenReturn(Stream
					.of(theatreXion, theatreViva).collect(Collectors.toList()));
			assertEquals(2, theatreService.viewAllTheatre().size());
		
		}


}
