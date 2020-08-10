package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("select m from Movie m order by m.movieRating desc")
	List<Movie> topThreeMovies();
	

	//@Query("SELECT m from Movie m where m.movieId=?1")
	Movie findByMovieId(long movieId);

	@Modifying
	@Query("UPDATE Movie m SET m.status=?1 WHERE m.movieId=?2")
	void deleteMovieById(boolean status, long movieId);

	@Query("SELECT m FROM Movie m WHERE m.status = ?1")
	List<Movie> findAllMovies(boolean status);
	
}
