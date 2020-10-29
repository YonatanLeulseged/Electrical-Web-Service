package edu.csus.csc131.restDemo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.Integer;


import java.util.List;

interface UsagesRepository  extends JpaRepository<Usages, Long> {
	
	List<Usages> findByUserId(Long userId);
	List<Usages> findByUserIdAndYear(Long userId, 
			Integer year);
	List<Usages> findByUserIdAndYearAndMonth(Long userId, 
			Integer year, 
			Integer month);
	List<Usages> findByUserIdAndYearAndMonthAndDate(Long userId, 
			Integer year, 
			Integer month,
			Integer date);
	List<Usages> findByUserIdAndYearAndDate(Long userId, 
			Integer year,
			Integer date);
	List<Usages> findByUserIdAndMonthAndDate(Long userId,
			Integer month,
			Integer date);
	List<Usages> findByUserIdAndDate(Long userId,
			Integer date);
	List<Usages> findByUserIdAndMonth(Long userId,
			Integer month);
	List<Usages> findByDate(Integer date);
	List<Usages> findByMonth(Integer month);
	List<Usages> findByMonthAndDate(Integer month,
			Integer date);
	List<Usages> findByYear(Integer year);
	List<Usages> findByYearAndDate(Integer year,
			Integer date);
	List<Usages> findByYearAndMonth(Integer year,
			Integer month);
	List<Usages> findByYearAndMonthAndDate(Integer year, 
			Integer month,
			Integer date);
}
