package com.cg.movie.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.TheatreRepository;
import com.cg.movie.entities.Theatre;
import com.cg.movie.exception.TheatreNotFoundException;

@Service
public class TheatreServiceImpl implements ITheatreService {

	@Autowired
	TheatreRepository theatreRepo;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Theatre addTheatre(Theatre theatre) {
		return theatreRepo.save(theatre);
	}

	@Override
	public void deleteTheatre(Theatre theatre) {
		//theatreRepo.delete(theatre);
		System.out.println(theatre.getTheatreId());
		if(theatreRepo.existsById(theatre.getTheatreId()))
		{
			System.out.println(theatre.getTheatreId());
			theatreRepo.delete(theatre);
			logger.info("Delete theatre of id "+theatre.getTheatreId());
		}
		else
		{
			logger.error("Theatre not found with "+theatre.getTheatreId());
			throw new TheatreNotFoundException("Theatre Not Found");
		}
	}
	
	@Override
	public void updateTheatre(Theatre theatre) {
		theatreRepo.save(theatre);
	}

	@Override
	public List<Theatre> viewAllTheatre() {
		// TODO Auto-generated method stub
		 List<Theatre> theatre=theatreRepo.findAll();
		 if(theatre.size()==0)
		 {
			 logger.error("No Theatre Found");
			 throw new TheatreNotFoundException("No theatre added yet");
		 }
		 else
		 {
			 logger.info("Theatres found successfully");
			 return theatre;
		 }
		
		
	}
	
	@Override
	public Theatre getTheatreById(long theatreId) {
		// TODO Auto-generated method stub
		System.out.println(theatreId);
		Theatre theatre= theatreRepo.getOne(theatreId);
		if(theatre==null)
		{
			logger.error("Theatre not found with "+theatreId);
			throw new TheatreNotFoundException("Theatre Not Found");
		}
		else {
			logger.info(" theatre found of id "+theatreId);
		return theatre;}
	}
	
	@Override
	public List<Theatre>searchTheater(String theatre) {
		if (theatreRepo.findAll()== null)
		{
			throw new TheatreNotFoundException("Theatre not found");
		}
		List<Theatre> listTheatre= new ArrayList<Theatre>();
		theatreRepo.findAll().forEach(e-> {
			String theatreName = e.getTheatreName();
			if(theatreName.equals(theatre)) {
				listTheatre.add(e);
			}
		});
		
		return listTheatre;
	}
}
