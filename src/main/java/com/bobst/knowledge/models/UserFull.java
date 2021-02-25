package com.bobst.knowledge.models;

public class UserFull{
	//u.email, c.site, u.position, u.primaryPl, a.VIEWS, a.CONTRIBUTIONS, a.KBA, a.WIKIS
	String email;
	String site;
	String position;
	String primaryPl;
	
	Integer views;
	Integer contributions;
	Integer kba;
	Integer wikis;
	
	public UserFull() {
	
	}

	public UserFull(String email, String site, String position, String primaryPl, Integer views, Integer contributions,
			Integer kba, Integer wikis) {
		super();
		this.email = email;
		this.site = site;
		this.position = position;
		this.primaryPl = primaryPl;
		this.views = views;
		this.contributions = contributions;
		this.kba = kba;
		this.wikis = wikis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPrimaryPl() {
		return primaryPl;
	}

	public void setPrimaryPl(String primaryPl) {
		this.primaryPl = primaryPl;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getContributions() {
		return contributions;
	}

	public void setContributions(Integer contributions) {
		this.contributions = contributions;
	}

	public Integer getKba() {
		return kba;
	}

	public void setKba(Integer kba) {
		this.kba = kba;
	}

	public Integer getWikis() {
		return wikis;
	}

	public void setWikis(Integer wikis) {
		this.wikis = wikis;
	}

	

}
