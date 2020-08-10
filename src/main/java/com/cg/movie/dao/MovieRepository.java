package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	//@Query("select m from Movie where m.movieId=movieId")
	Movie findById(long movieId);
}
