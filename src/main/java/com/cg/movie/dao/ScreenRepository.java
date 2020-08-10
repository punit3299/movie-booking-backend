package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

	@Modifying
	@Query("UPDATE Screen screen SET screen.status = ?1 WHERE screen.screenId = ?2")
	void deleteScreenById(boolean status, long screenId);
}
