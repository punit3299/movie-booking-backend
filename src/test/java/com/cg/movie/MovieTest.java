package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.exception.MoviesNotFoundException;
import com.cg.movie.services.IMovieService;

@SpringBootTest
public class MovieTest {

	@MockBean
	MovieRepository movieDAO;

	@Autowired
	IMovieService movieService;

	@Test
	@Description("This is for testing movie is added")
	public void addMovieTest() {
		Movie movie = createMovie(1170000011L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);

		when(movieDAO.save(Mockito.any(Movie.class))).thenReturn(movie);

		assertEquals(movie, movieService.addMovie(new Movie()));
	}

	@Test
	@Description("This is for Testing if movieId not found then throw MovieNotFoundException")
	public void deleteMovieById_will_throw_MovieNotFoundException() {

		long movieId = 123456782L;
		
		when(movieDAO.existsById((Mockito.anyLong()))).thenReturn(false);
	
		Exception exception=assertThrows(MoviesNotFoundException.class, () -> {
			movieService.deleteMovieById(movieId);
		});
		
		String expected_exception ="Movie with"+movieId+"doesn't Exist";
		String actual_message=exception.getMessage();
		
		assertTrue(actual_message.contains(expected_exception));
	}

	@Test
	@Description("This is for Testing gettingAllMovies")
	public void getAllMovies() {

		List<Movie> movies = new ArrayList<>();

		Movie movie1 = createMovie(1170000011L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);
		movies.add(movie1);

		Movie movie2 = createMovie(1170000012L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);
		movies.add(movie2);

		when(movieDAO.findAll()).thenReturn(movies);

		Set<Movie> response = movieService.findAllMovie();

		assertTrue(response.size() > 0);

		assertTrue(response.contains(movie1));

	}

	@Test
	@Description("This test is for verifying deleteMovieById method is executed 1 time")
	public void deleteMovieTest() {
		
		when(movieDAO.existsById((Mockito.anyLong()))).thenReturn(true);

		movieService.deleteMovieById(1170000011L);

		verify(movieDAO, times(1)).deleteMovieById(Mockito.anyLong());
	}

	public Movie createMovie(Long id, String name, String genre, String director, Double length) {
		Movie movie = new Movie();
		movie.setMovieId(id);
		movie.setMovieName(name);
		movie.setMovieGenre(genre);
		movie.setMovieDirector(director);
		movie.setMovieLength(length);
		movie.setMovieReleaseDate(new Timestamp(2014, 06, 02, 2, 00, 00, 00));

		return movie;
	}
}
