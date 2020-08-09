package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
