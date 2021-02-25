package com.bobst.knowledge.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KPI_USER {
	
	@Id
	private String email;
	private String locationId;
	private String primaryPl;
	private String firstName;
	private String lastName;
	private String occupationRate; 
	private String titleKnowledge; 
	private String lastLoginKnowledge;;
	private String statusKnowledge;
	private String status_global;
	private String userName;
	private String position;
	private String positionId;
	private String inWfp;
	private String titleWfp;
	private String controlling;
	private String expertise;
	
	public KPI_USER() {
		
	}

	public KPI_USER(String email, String locationId, String primaryPl, String firstName, String lastName,
			String occupationRate, String titleKnowledge, String lastLoginKnowledge, String statusKnowledge,
			String status_global, String userName, String position, String positionId, String inWfp, String titleWfp,
			String controlling, String expertise) {
		super();
		this.email = email;
		this.locationId = locationId;
		this.primaryPl = primaryPl;
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupationRate = occupationRate;
		this.titleKnowledge = titleKnowledge;
		this.lastLoginKnowledge = lastLoginKnowledge;
		this.statusKnowledge = statusKnowledge;
		this.status_global = status_global;
		this.userName = userName;
		this.position = position;
		this.positionId = positionId;
		this.inWfp = inWfp;
		this.titleWfp = titleWfp;
		this.controlling = controlling;
		this.expertise = expertise;
	}
	
	public boolean validate()
	{
		return (!email.isEmpty());
	}
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getPrimaryPl() {
		return primaryPl;
	}

	public void setPrimaryPl(String primaryPl) {
		this.primaryPl = primaryPl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupationRate() {
		return occupationRate;
	}

	public void setOccupationRate(String occupationRate) {
		this.occupationRate = occupationRate;
	}

	public String getTitleKnowledge() {
		return titleKnowledge;
	}

	public void setTitleKnowledge(String titleKnowledge) {
		this.titleKnowledge = titleKnowledge;
	}

	public String getLastLoginKnowledge() {
		return lastLoginKnowledge;
	}

	public void setLastLoginKnowledge(String lastLoginKnowledge) {
		this.lastLoginKnowledge = lastLoginKnowledge;
	}

	public String getStatusKnowledge() {
		return statusKnowledge;
	}

	public void setStatusKnowledge(String statusKnowledge) {
		this.statusKnowledge = statusKnowledge;
	}

	public String getStatus_global() {
		return status_global;
	}

	public void setStatus_global(String status_global) {
		this.status_global = status_global;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getInWfp() {
		return inWfp;
	}

	public void setInWfp(String inWfp) {
		this.inWfp = inWfp;
	}

	public String getTitleWfp() {
		return titleWfp;
	}

	public void setTitleWfp(String titleWfp) {
		this.titleWfp = titleWfp;
	}

	public String getControlling() {
		return controlling;
	}

	public void setControlling(String controlling) {
		this.controlling = controlling;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@Override
	public String toString() {
		return "KPI_USER [email=" + email + ", locationId=" + locationId + ", primaryPl=" + primaryPl + ", firstName="
				+ firstName + ", lastName=" + lastName + ", occupationRate=" + occupationRate + ", titleKnowledge="
				+ titleKnowledge + ", lastLoginKnowledge=" + lastLoginKnowledge + ", statusKnowledge=" + statusKnowledge
				+ ", status_global=" + status_global + ", userName=" + userName + ", position=" + position
				+ ", positionId=" + positionId + ", inWfp=" + inWfp + ", titleWfp=" + titleWfp + ", controlling="
				+ controlling + ", expertise=" + expertise + "]";
	}
	
		
	

}
