package com.cg.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.LanguageRepository;
import com.cg.movie.entities.Language;

@Service
public class LanguageServiceImpl implements ILanguageService {

	@Autowired
	LanguageRepository languageRepo;
	
	@Override
	public Language addLanguage(Language language) {
		return languageRepo.save(language);
	}

}
