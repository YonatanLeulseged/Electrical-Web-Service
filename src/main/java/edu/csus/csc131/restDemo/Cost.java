package edu.csus.csc131.restDemo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Cost {
	
	private Long userId;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone="America/Los_Angeles")
	private Date startTime;
	private Date endTime;
	private double value = 0;
	
	
	public Cost() {
	}
	
	public Cost(Long userId, Date startTime, Date endTime, double value) {
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.value = value;
	}
	
	public void setUserId( Long userId ) {
		this.userId = userId;
	}
	
	public Long getUserId( ) {
		return userId;
	}
	
	public void addValue(double value) {
		this.value += value;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue( double value ) {
		this.value = value;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		if ( (this.endTime == null) || (this.endTime.before(endTime))) {
			this.endTime = endTime;
		}
	}
	
	public void setStartTime(Date startTime) {
		
		if ( (this.startTime == null) || (startTime.before(this.startTime)) ) {
			this.startTime = startTime;
		}
		
	}
	
}
