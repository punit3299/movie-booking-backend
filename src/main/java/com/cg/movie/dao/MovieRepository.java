package com.cg.movie.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{


}
