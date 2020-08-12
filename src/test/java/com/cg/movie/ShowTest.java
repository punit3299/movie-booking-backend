package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.ShowRepository;
import com.cg.movie.entities.Show;
import com.cg.movie.services.IShowService;
import com.cg.movie.services.ShowServiceImpl;


@SpringBootTest
public class ShowTest {
   
	@Autowired
	IShowService showService;
	
	@MockBean
	ShowRepository showRepo;
	
	@Test
	public void addShowTest() {
		Show show =new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker");
        when(showRepo.save(show)).thenReturn(show);	
        assertEquals(show, showService.addShow(show));
	}


	@Test
	public void deleteShowTest() {
		
		//Show show = new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker");
		when(showRepo.existsById(Mockito.anyLong())).thenReturn(true);

		showService.deleteShowById(new Long(500));
	
		verify(showRepo, times(1)).deleteShowById(Mockito.anyLong());
	}
	
	@Test
	public void getShowByMovieIdTest()
	{
		List<Show> shows=Stream.of(new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker"), (new Show(new Long(501), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker"))).collect(Collectors.toList());
	    when(showRepo.findShowByMovieId(new Long(6001))).thenReturn(shows);
	    assertEquals(2,showService.getShowByMovieId(new Long(6001)).size());
	}
	
	@Test
	public void getShowByTheatreIdTest()
	{
		List<Show> shows=Stream.of(new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker"), (new Show(new Long(501), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker"))).collect(Collectors.toList());
	    when(showRepo.findShowByTheatreId(new Long(7001))).thenReturn(shows);
	    assertEquals(2,showService.getShowByTheatreId(new Long(7001)).size());
	}
 
	
}
