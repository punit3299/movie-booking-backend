package com.cg.movie.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query("select m from Movie m order by m.movieRating desc")
	List<Movie> topThreeMovies();
	
	Movie findByMovieId(long movieId);

	@Modifying
	@Query("UPDATE Movie m SET m.status=1 WHERE m.movieId=?1")
	void deleteMovieById(long movieId);

	@Query("SELECT movie FROM Movie movie WHERE movie.status = 0")
	List<Movie> findAllMovies();
	
}
