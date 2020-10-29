package edu.csus.csc131.restDemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "periods")
public class Rate {
	
	@JsonIgnore
	private @Id @GeneratedValue Long id;
	private int startTime;
	private int endTime;

	public Rate( ) {	
	}
	
	public Rate(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getStartTime(){
		return this.startTime;
	}
		
	public int getEndTime(){
		return this.endTime;
	}

	
	public void setStartTime( int startTime){
		this.startTime = startTime;
	}
	
	public void setEndTime( int endTime ){
		this.endTime = endTime;
	}
	
	
}