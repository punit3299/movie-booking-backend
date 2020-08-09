package com.cg.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.LanguageRepository;
import com.cg.movie.entities.Language;
import com.cg.movie.services.LanguageServiceImpl;

@SpringBootTest
class LanguageTest {

	@Autowired
	LanguageServiceImpl languageService;
	
	@MockBean
	LanguageRepository languageRepo;
	
	@Test
	public void addLanguageTest() {
		Language language=new Language(new Long(1), "Hindi");
		when(languageRepo.save(language)).thenReturn(language);
		assertEquals(language, languageService.addLanguage(language));
	}

}
