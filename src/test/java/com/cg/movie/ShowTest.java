package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Show;
import com.cg.movie.exception.MovieDoesntExistException;
import com.cg.movie.exception.TheatreNotFoundException;
import com.cg.movie.services.IShowService;

@SpringBootTest
public class ShowTest {

	@Autowired
	private IShowService showService;

	@MockBean
	private ShowRepository showRepo;

	@MockBean
	private TheatreRepository theatreRepo;

	@Test
	public void deleteShowTest() {

		when(showRepo.existsById(Mockito.anyLong())).thenReturn(true);

		showService.deleteShowById(new Long(500));

		verify(showRepo, times(1)).deleteShowById(Mockito.anyLong());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void getAllShowsTest() {
		Show show = new Show(new Long(500), new Timestamp(2020, 06, 19, 9, 00, 00, 000),
				new Timestamp(2020, 06, 19, 11, 00, 00, 000), "Joker", "English");

		Show show1 = new Show(new Long(500), new Timestamp(2020, 06, 19, 9, 00, 00, 000),
				new Timestamp(2020, 06, 19, 11, 00, 00, 000), "The Fault in our stars", "English");

		long theatreId = new Long(7);

		when(showRepo.findAllShows(Mockito.anyLong())).thenReturn(Stream.of(show, show1).collect(Collectors.toList()));

		assertEquals(2, showService.getAllShow(theatreId).size());

	}


	public void getAllShowTest() {

		long theatreId = new Long(7);

		when(theatreRepo.existsById(Mockito.anyLong())).thenReturn(false);

		Exception exception = assertThrows(TheatreNotFoundException.class, () -> {
			showService.getAllShow(theatreId);
		});

		String expected_exception = "Theatre with id" + theatreId + "not found";

		String actual_message = exception.getMessage();

		assertTrue(actual_message.contains(expected_exception));

	}

	@Test
	public void getShowByMovieIdTest() {
		List<Show> shows = Stream.of(
				new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker","English"),
				(new Show(new Long(501), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker","English")))
				.collect(Collectors.toList());
		when(showRepo.findShowByMovieId(new Long(6001))).thenReturn(shows);
		assertThrows(MovieDoesntExistException.class, () ->{showService.getShowByMovieId(new Long(6001));});
	}

	@Test
	public void getShowByTheatreIdTest() {
		List<Show> shows = Stream.of(
				new Show(new Long(500), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker","English"),
				(new Show(new Long(501), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), "Joker","English")))
				.collect(Collectors.toList());
		when(showRepo.findShowByTheatreId(new Long(7001))).thenReturn(shows);
		assertThrows(TheatreNotFoundException.class, () ->{showService.getShowByTheatreId(new Long(7001));});
	}

}
