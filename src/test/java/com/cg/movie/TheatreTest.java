package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

	@Test
	public void addTheatreTest() {
		Theatre theatre = new Theatre(new Long(1), "Xion", 5, "Mohit", 7973657728L);
		when(theatreRepo.save(theatre)).thenReturn(theatre);
		assertEquals(theatre, theatreService.addTheatre(theatre));
	}
	
	  @Test 
	  public void deleteTheatreTest() throws Exception{ 
	  Theatre theatre = new Theatre(new Long(6), "Xion", 5, "Mohit", 7973657728L);
	  //theatreRepo.save(theatre);
	  when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(true);
	  theatreService.deleteTheatre(theatre);
	  verify(theatreRepo,times(1)).delete(theatre); 
	  }
	  
	  @Test
	  public void theatreNotFoundExceptionTest() throws Exception
	  {
		  Theatre theatre = new Theatre(new Long(6), "Xion", 5, "Mohit", 7973657728L);
		  when(theatreRepo.existsById(theatre.getTheatreId())).thenReturn(false);
		  assertThrows(TheatreNotFoundException.class, ()->{theatreService.deleteTheatre(theatre);});
	  }
	  
		@Test
		public void viewAllTheaterTest() {
			Theatre x_theatre = new Theatre(new Long(1), "Xion", 5, "Mohit", 7973657728L);
			Theatre y_theatre = new Theatre(new Long(2), "yion", 4, "Rohit", 7973658828L);
			when(theatreRepo.findAll()).thenReturn(Stream
					.of(x_theatre, y_theatre).collect(Collectors.toList()));
			assertEquals(2, theatreService.viewAllTheatre().size());
		
		}

}
