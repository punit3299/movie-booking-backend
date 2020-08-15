  
package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.movie.entities.Screen;

@Transactional
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

	@Modifying
	@Query("UPDATE Screen screen SET screen.status = 1 WHERE screen.screenId = ?1")
	void deleteScreenById(long screenId);
	
	@Modifying
	@Query("SELECT screen FROM Screen screen WHERE screen.status = 1 AND screen.theatre.theatreId = ?1")
	List<Screen> findAll(long theatreId);
	
	@Query("SELECT screen FROM Screen screen WHERE screen.screenId=?1 AND screen.status=0")
	Screen findByScreenId(long screenId);
}