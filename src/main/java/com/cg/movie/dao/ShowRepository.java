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

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	@Query("SELECT s from Show s WHERE ((s.showStartTime <= :startTime AND s.showEndTime >=:startTime)"
			+ " OR (s.showStartTime <= :endTime AND s.showEndTime >=:endTime)"
			+ "OR (s.showStartTime >= :startTime AND s.showEndTime <=:endTime))"
			+ "AND s.screen.screenId = :screenId "
			+ "AND s.status=0")
	public List<Show> timePeriodOverlap(@Param("startTime") Timestamp startTime,@Param("endTime") Timestamp endTime , 
			@Param("screenId") long screenId);

	@Transactional
	@Modifying
	@Query("UPDATE Show s SET s.status=?1 WHERE s.showId=?2")
	void deleteShowById(boolean status, long showId);
	
	@Query("SELECT s from Show s WHERE s.status=0 ")
	List<Show> findAllShows();
	
	
    @Query(value="select show from Show show where show.movieId=?1",nativeQuery=true)
	public List<Show> findShowByMovieId(Long id);
	
	
    @Query(value="select show from Show show where show.theatreId=?1",nativeQuery=true)
	public List<Show> findShowByTheatreId(Long id);
	
}
