package com.cg.movie.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dao.ShowRepository;
import com.cg.movie.entities.Movie;
import com.cg.movie.entities.Show;

@Service
public class ShowServiceImpl implements IShowService {

	@Autowired
	ShowRepository showRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	@Override
	public Show addShow(Show show) {
		return showRepo.save(show);
	}

	@Override
	public long addNewShow(long movieId, Show show) {
		
	    Movie movie=movieRepo.findById(movieId);
		if(movieRepo.findById(movieId)!=null)
		{
			show.setMovie(movie);
			Show addShow=showRepo.save(show);
			return addShow.getShowId();
		}
		return movieId;
	}

	@Override
	public void deleteShowById(long showId) {
		
		showRepo.deleteById(showId);
		
	}

	@Override
	public Set<Show> getAllShow() {
	 
		List<Show> showList=showRepo.findAll();
		Set<Show> showList1=new HashSet<>(showList);
		return showList1;
	}
	
	
	
	

}
