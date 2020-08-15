package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.LanguageRepository;
import com.cg.movie.dao.MovieRepository;
import com.cg.movie.entities.Language;
import com.cg.movie.entities.Movie;
import com.cg.movie.exception.InValidDataEntryException;
import com.cg.movie.exception.MovieDoesntExistException;

@Service
public class LanguageServiceImpl implements ILanguageService {

	@Autowired
	LanguageRepository languageRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	@Override
	public Language addLanguage(Language language,long movieId) {
		return language;
		
		/*
		 * Movie movie=movieRepo.findById(movieId).get(); if(movie!=null) { Language
		 * filterLanguage=languageRepo.languageRepitition(language.getLanguageName(),
		 * movieId); if(filterLanguage==null) { return languageRepo.save(language); }
		 * throw new InValidDataEntryException("Enter languages again"); } else throw
		 * new MovieDoesntExistException("Movie with Id"+" "+movieId+"doesn't exist");
		 */
		
	}

}
