package com.cg.movie.services;

import com.cg.movie.entities.Language;

public interface ILanguageService {
	
	 Language addLanguage(Language language,long movieId);

}
