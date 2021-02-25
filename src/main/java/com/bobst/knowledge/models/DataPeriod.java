package com.bobst.knowledge.models;

public class DataPeriod {
	
	String period;
	Integer data;
	
	DataPeriod() {
		
	}

	public DataPeriod(String period, Integer data) {
		super();
		this.period = period;
		this.data = data;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}
	
	

}
