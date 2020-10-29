package edu.csus.csc131.restDemo;

import static org.junit.jupiter.api.Assertions.*;
//import java.util.Date;
//import java.util.List;
import java.util.Calendar;
import java.lang.Integer;
import java.util.GregorianCalendar;
import java.util.Date;


import org.junit.jupiter.api.Test;


class CostTest {

	@Test
	
	void testCost_1() {
		Cost one = new Cost();
		one.setUserId(2L);
		one.setStartTime(null);
		one.setEndTime(null);
		one.setValue(20);
		one.addValue(5);
		assertEquals(25, one.getValue());
		
		one.getUserId();
		one.getStartTime();
		one.getEndTime();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,10);
		Date start = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY,11);
		Date end = cal.getTime();
		
		Cost two = new Cost(2L, start, end, 6);
		
		cal.set(Calendar.HOUR_OF_DAY,12);
		start = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY,13);
		end = cal.getTime();
		
		two.setStartTime(start);
		two.setEndTime(end);
		
		Cost three = new Cost(2L, start, end, 6);
		
		cal.set(Calendar.HOUR_OF_DAY,3);
		start = cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY,4);
		end = cal.getTime();
		
		three.setStartTime(start);
		three.setEndTime(end);
	}
}
