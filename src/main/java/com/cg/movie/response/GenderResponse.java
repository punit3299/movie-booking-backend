package com.cg.movie.response;

public class GenderResponse {
	
	private Long male;
	private Long female;
	private Long others;
	
	
	
	
	public GenderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenderResponse(Long male, Long female, Long others) {
		super();
		this.male = male;
		this.female = female;
		this.others = others;
	}
	public Long getMale() {
		return male;
	}
	public void setMale(Long male) {
		this.male = male;
	}
	public Long getFemale() {
		return female;
	}
	public void setFemale(Long female) {
		this.female = female;
	}
	public Long getOthers() {
		return others;
	}
	public void setOthers(Long others) {
		this.others = others;
	}
	
	

}
