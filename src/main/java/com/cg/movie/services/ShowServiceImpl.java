package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.ShowRepository;
import com.cg.movie.entities.Show;

@Service
public class ShowServiceImpl implements IShowService {

	@Autowired
	ShowRepository showRepo;
	
	@Override
	public Show addShow(Show show) {
		
		return showRepo.save(show);
	}

}
