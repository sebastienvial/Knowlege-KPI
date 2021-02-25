package com.bobst.knowledge.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class KPI_COMPANY {
	@Id
	private String locationId;
	
	private String location;
	private String site;
	private String groupSite;
	private String region;
	private String country;
	
	public KPI_COMPANY() {
		
	}

	public KPI_COMPANY(String locationId, String location, String site, String groupSite,
			String region, String country) {
		super();
		this.locationId = locationId;
		this.location = location;
		this.site = site;
		this.groupSite = groupSite;
		this.region = region;
		this.country = country;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getGroupSite() {
		return groupSite;
	}

	public void setGroupSite(String groupSite) {
		this.groupSite = groupSite;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "KPI_COMPANY [locationId=" + locationId + ", location=" + location + ", site=" + site + ", groupSite="
				+ groupSite + ", region=" + region + ", country=" + country + "]";
	}
	
	

}
