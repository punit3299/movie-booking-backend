package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.services.MovieServiceImpl;

@SpringBootTest
public class MovieTest {
	@MockBean
	MovieRepository movieDAO;

	@Autowired
	MovieServiceImpl movieServiceImpl;

	@SuppressWarnings("deprecation")
	@Test
	public void addMovie() {
		Movie movie = createMovie(1170000011L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);

		when(movieDAO.save(Mockito.any(Movie.class))).thenReturn(movie);

		assertEquals(movie, movieServiceImpl.addMovie(new Movie()));
	}

	@Test
	public void getAllMovies() {

		List<Movie> movies = new ArrayList<>();

		Movie movie1 = createMovie(1170000011L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);
		movies.add(movie1);

		Movie movie2 = createMovie(1170000012L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);
		movies.add(movie2);

		when(movieDAO.findAll()).thenReturn(movies);

		Set<Movie> response = movieServiceImpl.findAllMovie();

		assertTrue(response.size() > 0);

		assertTrue(response.contains(movie1));

	}

	@Test
	public void deleteMovieTest() {
		//Movie movie1 = createMovie(1170000011L, "The Fault in our stars", "Young Adult Fiction", "John Boone", 02.13);
		
		movieServiceImpl.deleteMovieById(1170000011L);
	
		verify(movieDAO, times(1)).deleteById(Mockito.anyLong());
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
