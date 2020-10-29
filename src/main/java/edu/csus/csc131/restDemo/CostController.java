package edu.csus.csc131.restDemo;

import java.util.List;
import java.util.TimeZone;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.Integer;
import java.util.GregorianCalendar;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CostController {

  private UsagesRepository repository;
  private RatePlanRepository rateRepository;
  private CostModelAssembler assembler;
  
  

  CostController(UsagesRepository repository, CostModelAssembler assembler, RatePlanRepository rateRepository) {
    this.repository = repository;
    this.assembler = assembler;
    this.rateRepository = rateRepository;
  }
  
  // Aggregate root
  //Get all
  @GetMapping("/costs")
  EntityModel<Cost> all(@RequestParam(required = false) Long userId,
		@RequestParam(required = false) Integer year,
		@RequestParam(required = false) Integer month,
		@RequestParam(required = false) Integer date) {
	 List<Usages> usagesList = new ArrayList<Usages>();

	 if (userId == null && year == null  && month == null && date == null ) {
		  usagesList = repository.findAll();
	 }
	 else if( userId != null ) {
		 if(year == null && month == null && date == null) {
			 usagesList = repository.findByUserId(userId);
		 }
		 else if (year == null && month == null && date != null) {
			 usagesList = repository.findByUserIdAndDate(userId, date);
		 }
		 else if (year == null && month != null && date == null) {
			 usagesList = repository.findByUserIdAndMonth(userId, month);
		 }
		 else if (year == null && month != null && date != null) {
			 usagesList = repository.findByUserIdAndMonthAndDate(userId, month, date);
		 }
		 else if (year != null && month == null && date == null) {
			 usagesList = repository.findByUserIdAndYear(userId, year);
		 }
		 else if (year != null && month == null && date != null) {
			 usagesList = repository.findByUserIdAndYearAndDate(userId, year, date);
		 }
		 else if (year != null && month != null && date == null) {
			 usagesList = repository.findByUserIdAndYearAndMonth(userId, year, month);
		 }
		 else if (year != null && month != null && date != null) {
			 usagesList = repository.findByUserIdAndYearAndMonthAndDate(userId, year, month, date);
		 }
	 }
	 else {
		if (year != null && month != null && date != null) {
			usagesList = repository.findByYearAndMonthAndDate(year, month, date);
		}
		if (year != null && month != null && date == null) {
			usagesList = repository.findByYearAndMonth(year, month);
		}
		if (year != null && month == null && date != null) {
			usagesList = repository.findByYearAndDate(year, date);
		}
		if (year != null && month == null && date == null) {
			usagesList = repository.findByYear(year);
		}
		if (year == null && month != null && date != null) {
			usagesList = repository.findByMonthAndDate(month, date);
		}
		if (year == null && month == null && date != null) {
			usagesList = repository.findByDate(date);
		 }
		if (year == null && month != null && date == null) {
			usagesList = repository.findByMonth(month);
		 }
	 }
	 
	 Cost cost = new Cost();
	 
		 

	 for(int i = 0; i < usagesList.size(); i++) {
		 Usages use = usagesList.get(i);
		 double addValue = use.getValue();
		 double mulValue = 0;
		 
		 
		 
		 TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		 Calendar calendar = new GregorianCalendar(timeZone);
		 calendar.setTime(use.getStartTime());
		 
		 Integer getMonth = calendar.get(Calendar.MONTH) + 1;
		 Integer getHour = calendar.get(Calendar.HOUR_OF_DAY);
		 
		 if (getMonth >= 6 && getMonth <= 9 ) {
			 Plan plan = new Plan( rateRepository.findByName("summer").getName(), rateRepository.findByName("summer").getRates());
			 
			 RatePlan[] ratePlans = plan.getRates().toArray(new RatePlan[0]);
			 for (int j = 0; j < ratePlans.length; j++) {
				 Rate[] getRates = ratePlans[j].getPeriods().toArray(new Rate[0]);
				 for (int k = 0; k < getRates.length; k++) {
					 if ( getHour >= getRates[k].getStartTime() && getHour < getRates[k].getEndTime() ) {
						 mulValue = ratePlans[j].getRate();
					 }
				 }
			 }
			 
		 }
		 else {
			 Plan plan = new Plan( rateRepository.findByName("non-summer").getName(), rateRepository.findByName("non-summer").getRates());
			 
			 RatePlan[] ratePlans = plan.getRates().toArray(new RatePlan[0]);
			 for (int j = 0; j < ratePlans.length; j++) {
				 Rate[] getRates = ratePlans[j].getPeriods().toArray(new Rate[0]);
				 for (int k = 0; k < getRates.length; k++) {
					 if ( getHour >= getRates[k].getStartTime() && getHour < getRates[k].getEndTime() ) {
						 mulValue = ratePlans[j].getRate();
					 }
				 }
			 }
		 }
		 
		 cost.setUserId(use.getUserId());
		 cost.setStartTime(use.getStartTime());
	 	 cost.setEndTime(use.getEndTime());
		 
		 addValue *= mulValue;
		 cost.addValue(addValue);
	 }
	 
	 cost.setValue(Math.round(cost.getValue() * 100.0) / 100.0);
	 
	 return assembler.toModel(cost);
	 
 }
  
}