package com.cg.movie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "language_table")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long languageId;
	private String languageName;

	public Language() {
		super();
	}
	
	public Language(String languageName) {
		super();
		this.languageName = languageName;
	}


	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}