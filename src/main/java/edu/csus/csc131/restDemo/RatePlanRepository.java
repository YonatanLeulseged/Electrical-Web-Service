package edu.csus.csc131.restDemo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RatePlanRepository extends JpaRepository<Plan, String> {
	Plan findByName(String name);
}




