package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("SELECT admin.email FROM Admin admin")
	public List<String> getAllEmail();
	
	@Query("SELECT admin.adminPassword FROM Admin admin WHERE admin.email = ?1")
	public String getPassword(String email);
}
