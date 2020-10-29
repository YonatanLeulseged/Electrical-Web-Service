package edu.csus.csc131.restDemo;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hourlyUsages")
public class Usages {
	
	
	
	private @Id @GeneratedValue Long id;
	private Long userId;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone="America/Los_Angeles")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone="America/Los_Angeles")
	private Date endTime;
	private double value;
	
	@JsonIgnore
	private Integer year;
	@JsonIgnore
	private Integer month;
	@JsonIgnore
	private Integer date;
	
	public Usages( ) {	
	}

	public Usages(Long userId, Date startTime, Date endTime, double value) {
		super();
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.value = value;
		
		setYearMonthDate(startTime);
		
	}
	
	private void setYearMonthDate( Date startTime ) {
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		Calendar calendar = new GregorianCalendar(timeZone);
		calendar.setTime(startTime);
		
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.date = calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public Integer getYear() {
		return year;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public Integer getDate() {
		return date;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		setYearMonthDate(startTime);
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "Usage [userId=" + userId + ", Value=" + value + "]";
	}
	
}