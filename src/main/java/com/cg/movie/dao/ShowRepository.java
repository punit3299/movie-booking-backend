package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}
