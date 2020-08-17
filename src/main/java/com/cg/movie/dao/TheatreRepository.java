package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

	@Query("select t from Theatre t order by t.theatreRating desc")
	List<Theatre> topThreeTheatres();
	
	@Query("SELECT theatre FROM Theatre theatre WHERE theatre.status = ?1")
	List<Theatre> findAllTheatres(boolean status);

	@Query("SELECT theatre FROM Theatre theatre WHERE theatre.theatreId=?1 AND theatre.status=0")
	Theatre findTheatreById(Long theatreId);
	
}
