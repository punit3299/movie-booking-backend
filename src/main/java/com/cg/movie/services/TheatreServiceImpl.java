package com.cg.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Theatre;

@Service
public class TheatreServiceImpl implements ITheatreService {

	@Autowired
	TheatreRepository theatreRepo;
	
	@Override
	public Theatre addTheatre(Theatre theatre) {
		return theatreRepo.save(theatre);
	}

	@Override
	public void deleteTheatre(Theatre theatre) {
		theatreRepo.delete(theatre);
	}
	
	@Override
	public void updateTheatre(Theatre theatre) {
		theatreRepo.save(theatre);
	}

	@Override
	public List<Theatre> viewAllTheatre() {
		// TODO Auto-generated method stub
		return theatreRepo.findAll();
		
	}
}
