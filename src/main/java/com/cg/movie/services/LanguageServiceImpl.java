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
		
	}

}
