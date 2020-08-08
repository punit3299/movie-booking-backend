package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

}
