package edu.csus.csc131.restDemo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {
	
	private @Id @GeneratedValue Long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<RatePlan> rates = new HashSet<RatePlan>();
	
	public Plan( ) {	
	}

	public Plan(String name) {
		super();
		this.name = name;
	}
	
	public Plan(String name, Set<RatePlan> rates) {
		super();
		this.name = name;
		this.rates = rates;
	}
	
	public Set<RatePlan> getRates() {
		return rates;
	}

	public void setRates(Set<RatePlan> rates) {
		this.rates.clear();
		this.rates.addAll(rates);
	}
	
	public void addRatePlan(RatePlan rates) {
		this.rates.add(rates);
	}
	
	public void removeRatePlan(RatePlan rates) {
		this.rates.remove(rates);
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