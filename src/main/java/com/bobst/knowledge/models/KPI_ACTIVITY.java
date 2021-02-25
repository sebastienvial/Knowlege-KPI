package com.bobst.knowledge.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class KPI_ACTIVITY {
	@Id
	private String EMAIL;
	private Integer VIEWS;
	private Integer CONTRIBUTIONS;
	private Integer GROUPS;
	private Integer BLOGS;
	private Integer COMMENTS;
	private Integer DISCUSSIONS;
	private Integer DOCUMENTS;
	private Integer EXPERTISE_ADDED;
	private Integer EXPERTISE_ENDORSED;
	private Integer IDEAS;
	private Integer QUESTIONS;
	private Integer KBA;
	private Integer KUDOS;
	private Integer LINKS;
	private Integer IMAGES;
	private Integer POLLS;
	private Integer POLL_VOTES;
	private Integer STATUS_UPDATES;
	private Integer TASKS;
	private Integer EVENTS;
	private Integer TAGS;
	private Integer VIDEOS;
	private Integer WIKIS;
	private Integer OVERVIEW;
	private String PERIOD;


	public KPI_ACTIVITY() {
	}


	public KPI_ACTIVITY(String eMAIL, Integer vIEWS, Integer cONTRIBUTIONS, Integer gROUPS, Integer bLOGS,
			Integer cOMMENTS, Integer dISCUSSIONS, Integer dOCUMENTS, Integer eXPERTISE_ADDED,
			Integer eXPERTISE_ENDORSED, Integer iDEAS, Integer qUESTIONS, Integer kBA, Integer kUDOS, Integer lINKS,
			Integer iMAGES, Integer pOLLS, Integer pOLL_VOTES, Integer sTATUS_UPDATES, Integer tASKS, Integer eVENTS,
			Integer tAGS, Integer vIDEOS, Integer wIKIS, Integer oVERVIEW, String pERIOD) {
		super();
		EMAIL = eMAIL;
		VIEWS = vIEWS;
		CONTRIBUTIONS = cONTRIBUTIONS;
		GROUPS = gROUPS;
		BLOGS = bLOGS;
		COMMENTS = cOMMENTS;
		DISCUSSIONS = dISCUSSIONS;
		DOCUMENTS = dOCUMENTS;
		EXPERTISE_ADDED = eXPERTISE_ADDED;
		EXPERTISE_ENDORSED = eXPERTISE_ENDORSED;
		IDEAS = iDEAS;
		QUESTIONS = qUESTIONS;
		KBA = kBA;
		KUDOS = kUDOS;
		LINKS = lINKS;
		IMAGES = iMAGES;
		POLLS = pOLLS;
		POLL_VOTES = pOLL_VOTES;
		STATUS_UPDATES = sTATUS_UPDATES;
		TASKS = tASKS;
		EVENTS = eVENTS;
		TAGS = tAGS;
		VIDEOS = vIDEOS;
		WIKIS = wIKIS;
		OVERVIEW = oVERVIEW;
		PERIOD = pERIOD;
	}


	public String getEMAIL() {
		return EMAIL;
	}


	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}


	public Integer getVIEWS() {
		return VIEWS;
	}


	public void setVIEWS(Integer vIEWS) {
		VIEWS = vIEWS;
	}


	public Integer getCONTRIBUTIONS() {
		return CONTRIBUTIONS;
	}


	public void setCONTRIBUTIONS(Integer cONTRIBUTIONS) {
		CONTRIBUTIONS = cONTRIBUTIONS;
	}


	public Integer getGROUPS() {
		return GROUPS;
	}


	public void setGROUPS(Integer gROUPS) {
		GROUPS = gROUPS;
	}


	public Integer getBLOGS() {
		return BLOGS;
	}


	public void setBLOGS(Integer bLOGS) {
		BLOGS = bLOGS;
	}


	public Integer getCOMMENTS() {
		return COMMENTS;
	}


	public void setCOMMENTS(Integer cOMMENTS) {
		COMMENTS = cOMMENTS;
	}


	public Integer getDISCUSSIONS() {
		return DISCUSSIONS;
	}


	public void setDISCUSSIONS(Integer dISCUSSIONS) {
		DISCUSSIONS = dISCUSSIONS;
	}


	public Integer getDOCUMENTS() {
		return DOCUMENTS;
	}


	public void setDOCUMENTS(Integer dOCUMENTS) {
		DOCUMENTS = dOCUMENTS;
	}


	public Integer getEXPERTISE_ADDED() {
		return EXPERTISE_ADDED;
	}


	public void setEXPERTISE_ADDED(Integer eXPERTISE_ADDED) {
		EXPERTISE_ADDED = eXPERTISE_ADDED;
	}


	public Integer getEXPERTISE_ENDORSED() {
		return EXPERTISE_ENDORSED;
	}


	public void setEXPERTISE_ENDORSED(Integer eXPERTISE_ENDORSED) {
		EXPERTISE_ENDORSED = eXPERTISE_ENDORSED;
	}


	public Integer getIDEAS() {
		return IDEAS;
	}


	public void setIDEAS(Integer iDEAS) {
		IDEAS = iDEAS;
	}


	public Integer getQUESTIONS() {
		return QUESTIONS;
	}


	public void setQUESTIONS(Integer qUESTIONS) {
		QUESTIONS = qUESTIONS;
	}


	public Integer getKBA() {
		return KBA;
	}


	public void setKBA(Integer kBA) {
		KBA = kBA;
	}


	public Integer getKUDOS() {
		return KUDOS;
	}


	public void setKUDOS(Integer kUDOS) {
		KUDOS = kUDOS;
	}


	public Integer getLINKS() {
		return LINKS;
	}


	public void setLINKS(Integer lINKS) {
		LINKS = lINKS;
	}


	public Integer getIMAGES() {
		return IMAGES;
	}


	public void setIMAGES(Integer iMAGES) {
		IMAGES = iMAGES;
	}


	public Integer getPOLLS() {
		return POLLS;
	}


	public void setPOLLS(Integer pOLLS) {
		POLLS = pOLLS;
	}


	public Integer getPOLL_VOTES() {
		return POLL_VOTES;
	}


	public void setPOLL_VOTES(Integer pOLL_VOTES) {
		POLL_VOTES = pOLL_VOTES;
	}


	public Integer getSTATUS_UPDATES() {
		return STATUS_UPDATES;
	}


	public void setSTATUS_UPDATES(Integer sTATUS_UPDATES) {
		STATUS_UPDATES = sTATUS_UPDATES;
	}


	public Integer getTASKS() {
		return TASKS;
	}


	public void setTASKS(Integer tASKS) {
		TASKS = tASKS;
	}


	public Integer getEVENTS() {
		return EVENTS;
	}


	public void setEVENTS(Integer eVENTS) {
		EVENTS = eVENTS;
	}


	public Integer getTAGS() {
		return TAGS;
	}


	public void setTAGS(Integer tAGS) {
		TAGS = tAGS;
	}


	public Integer getVIDEOS() {
		return VIDEOS;
	}


	public void setVIDEOS(Integer vIDEOS) {
		VIDEOS = vIDEOS;
	}


	public Integer getWIKIS() {
		return WIKIS;
	}


	public void setWIKIS(Integer wIKIS) {
		WIKIS = wIKIS;
	}


	public Integer getOVERVIEW() {
		return OVERVIEW;
	}


	public void setOVERVIEW(Integer oVERVIEW) {
		OVERVIEW = oVERVIEW;
	}


	public String getPERIOD() {
		return PERIOD;
	}


	public void setPERIOD(String pERIOD) {
		PERIOD = pERIOD;
	}


	@Override
	public String toString() {
		return "KPI_ACTIVITY [EMAIL=" + EMAIL + ", VIEWS=" + VIEWS + ", CONTRIBUTIONS=" + CONTRIBUTIONS + ", GROUPS="
				+ GROUPS + ", BLOGS=" + BLOGS + ", COMMENTS=" + COMMENTS + ", DISCUSSIONS=" + DISCUSSIONS
				+ ", DOCUMENTS=" + DOCUMENTS + ", EXPERTISE_ADDED=" + EXPERTISE_ADDED + ", EXPERTISE_ENDORSED="
				+ EXPERTISE_ENDORSED + ", IDEAS=" + IDEAS + ", QUESTIONS=" + QUESTIONS + ", KBA=" + KBA + ", KUDOS="
				+ KUDOS + ", LINKS=" + LINKS + ", IMAGES=" + IMAGES + ", POLLS=" + POLLS + ", POLL_VOTES=" + POLL_VOTES
				+ ", STATUS_UPDATES=" + STATUS_UPDATES + ", TASKS=" + TASKS + ", EVENTS=" + EVENTS + ", TAGS=" + TAGS
				+ ", VIDEOS=" + VIDEOS + ", WIKIS=" + WIKIS + ", OVERVIEW=" + OVERVIEW + ", PERIOD=" + PERIOD + "]";
	}
	
	
	
	
}
