package com.bobst.knowledge.models;

public class Configuration {
	private String period;
	private String site;
	private String pl;
	
	public Configuration() {
		
	}

	public Configuration(String period, String site, String pl) {
		super();
		this.period = period;
		this.site = site;
		this.pl = pl;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	@Override
	public String toString() {
		return "Configuration [period=" + period + ", site=" + site + ", pl=" + pl + "]";
	}
	
	

}
