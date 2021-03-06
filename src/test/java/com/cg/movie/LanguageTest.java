package com.cg.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.movie.dao.LanguageRepository;
import com.cg.movie.services.ILanguageService;

@SpringBootTest
class LanguageTest {

	@Autowired
	ILanguageService languageService;
	
	@MockBean
	LanguageRepository languageRepo;
	
	/*
	 * @Test public void addLanguageTest() { Language language=new Language(
	 * "Hindi", new Movie(1170000011L, "The Fault in our stars",
	 * "Young Adult Fiction", "John Boone", 02.13,null ,null));
	 * 
	 * when(languageRepo.languageRepitition(Mockito.anyString(),Mockito.anyLong())).
	 * thenReturn(null);
	 * 
	 * when(languageRepo.save(language)).thenReturn(language);
	 * 
	 * assertEquals(language, languageService.addLanguage(language, 1170000011L)); }
	 */
}
