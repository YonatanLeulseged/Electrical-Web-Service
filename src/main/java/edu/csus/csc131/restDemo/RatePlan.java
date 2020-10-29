package edu.csus.csc131.restDemo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RatePlan {
	@JsonIgnore
	private @Id @GeneratedValue Long id;
	private double rate;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rate> periods = new HashSet<Rate>();
	
	public RatePlan( ) {	
	}
	
	public RatePlan(String name, double rate) {
		super();
		this.name = name;
		this.rate = rate;
		this.periods = null;
	}
	
	public RatePlan(String name, double rate, int start, int end) {
		super();
		this.name = name;
		this.rate = rate;
		this.periods.add( new Rate( start, end ) );
	}

	public RatePlan(String name, double rate, int start, int end, int start2, int end2) {
		super();
		this.name = name;
		this.rate = rate;
		this.periods.add( new Rate( start, end ) );
		this.periods.add( new Rate( start2, end2 ) );
	}
	
	public Set<Rate> getPeriods() {
		return periods;
	}

	public void setPeriods(Set<Rate> periods) {
		this.periods.clear();
		this.periods.addAll(periods);
	}
	
	public void addRate(Rate periods) {
		this.periods.add(periods);
	}
	
	public void removeRate(Rate periods) {
		this.periods.remove(periods);
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName( String name ){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Plan [name=" + name + "]";
	}
	
}