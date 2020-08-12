package com.cg.movie.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Show;

@Transactional
@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	@Query("SELECT s from Show s WHERE ((s.showStartTime <= :startTime AND s.showEndTime >=:startTime)"
			+ " OR (s.showStartTime <= :endTime AND s.showEndTime >=:endTime)"
			+ "OR (s.showStartTime >= :startTime AND s.showEndTime <=:endTime))"
			+ "AND s.screen.screenId = :screenId "
			+ "AND s.status=0")
	public List<Show> timePeriodOverlap(@Param("startTime") Timestamp startTime,@Param("endTime") Timestamp endTime , 
			@Param("screenId") long screenId);

	
	@Modifying
	@Query("UPDATE Show s SET s.status=1 WHERE s.showId=?1")
	void deleteShowById(long showId);
	
	@Query("SELECT show from Show show WHERE show.theatre.theatreId=?1 AND show.status=0")
	List<Show> findAllShows(long thearteId);
		

    @Query(value="select * from show_table where movie_id=?1",nativeQuery=true)
	public List<Show> findShowByMovieId(Long id);
	
	
    @Query(value="select *  from show_table where theatre_id=?1",nativeQuery=true)
	public List<Show> findShowByTheatreId(Long id);
	
}


