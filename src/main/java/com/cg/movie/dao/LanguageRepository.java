package com.cg.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.movie.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

	@Query("SELECT language from Language language WHERE language.languageName=?1 AND language.movie.movieId=?2")
    Language languageRepitition(String languageName, long movieId);
}
