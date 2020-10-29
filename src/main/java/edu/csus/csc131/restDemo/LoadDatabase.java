package edu.csus.csc131.restDemo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
  TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
  Calendar calendar = new GregorianCalendar(timeZone);
	
  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository , RatePlanRepository ratePlanRepository, UsagesRepository usagesRepository ) {

    return args -> {
    	
  //  	userRepository.save(new User("Bilbo", "Baggins", "6000 J Street","Sacramento", "CA", "95819"));
  //      userRepository.save(new User("Frodo", "Baggins", "1234 K Street", "Stockton", "CA", "95814"));

  //      userRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));


	  
	  Plan summerPlan = new Plan( "summer" );
	  summerPlan.addRatePlan( new RatePlan("peak", 0.3941, 16, 21 ));
	  summerPlan.addRatePlan( new RatePlan("offPeak", 0.2209, 0, 12 ));
	  summerPlan.addRatePlan( new RatePlan("midPeak", 0.2671, 12, 16, 21,  24));
	  
	  Plan nonSummerPlan = new Plan( "non-summer" );
	  nonSummerPlan.addRatePlan( new RatePlan( "peak", 0.143, 17, 20 ));
	  nonSummerPlan.addRatePlan( new RatePlan( "offPeak", 0.1035, 20, 24, 0, 17 ));
	  
	  ratePlanRepository.save( summerPlan );
	  ratePlanRepository.save( nonSummerPlan );
	  
	  ratePlanRepository.findAll().forEach(ratePlan -> log.info("Preloaded " + ratePlan));

		/*
		 * Pre-set data tests individually checked
		 * 
		 * /* calendar.set(Calendar.YEAR, 2019); calendar.set(Calendar.MONTH, 1);
		 * calendar.set(Calendar.DAY_OF_MONTH, 28); calendar.set(Calendar.MINUTE, 0);
		 * calendar.set(Calendar.MILLISECOND, 0); calendar.set(Calendar.SECOND, 0);
		 * 
		 * calendar.set(Calendar.HOUR_OF_DAY, 0); Date startDate = calendar.getTime();
		 * calendar.set(Calendar.HOUR_OF_DAY, 1); Date endDate = calendar.getTime();
		 * usagesRepository.save(new Usages( 1L, startDate, endDate, 2.3194 )); //0 - 1
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 2);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 2.1297 )); //1 - 2
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 3);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 2.938 )); //2 - 3
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 4);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.8976 )); //3 - 4
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 5);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 4.5614 )); //4 - 5
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 6);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 4.8574 )); //5 - 6
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 7);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 1.6535 )); //6 - 7
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 8);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 3.9317 )); //7 - 8
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 9);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 4.5451 )); //8 - 9
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 10);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.2413 )); //9 - 10
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 11);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 4.5516 )); //10 - 11
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 12);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 3.9835 )); //11 - 12
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 13);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 1.9209 )); //12 - 13
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 14);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.8791 )); //13 - 14
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 15);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 4.172 )); //14 - 15
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 16);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.2504 )); //15 - 16
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 17);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 2.6519 )); //16 - 17
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 18);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 2.3504 )); //17 - 18
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 19);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.5544 )); //18 - 19
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 20);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 3.0608 )); //19 - 20
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 21);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 3.6872 )); //20 - 21
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 22);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 3.1436 )); //21 - 22
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 23);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.8238 )); //22 - 23
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.MONTH, 2);
		 * calendar.set(Calendar.DAY_OF_MONTH, 1); calendar.set(Calendar.HOUR_OF_DAY,
		 * 0); endDate = calendar.getTime(); usagesRepository.save(new Usages( 1L,
		 * startDate, endDate, 0.1951 )); //23 - 0
		 * 
		 * calendar.set(Calendar.DAY_OF_MONTH, 28); calendar.set(Calendar.HOUR_OF_DAY,
		 * 0); startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 1);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 1.7817 )); //0 - 1
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 2);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.2029 )); //1 - 2
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 3);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.7393 )); //2 - 3
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 4);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 0.4982 )); //3 - 4
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 5);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 1.1409 )); //4 - 5
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 6);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 1.9235 )); //5 - 6
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 7);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.9854 )); //6 - 7
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 8);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.7827 )); //7 - 8
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 9);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.9991 )); //8 - 9
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 10);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.7184 )); //9 - 10
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 11);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.5123 )); //10 - 11
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 12);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 0.5261 )); //11 - 12
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 13);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.8436 )); //12 - 13
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 14);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 3.0308 )); //13 - 14
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 15);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.1686 )); //14 - 15
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 16);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.4066 )); //15 - 16
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 17);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 3.8792 )); //16 - 17
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 18);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 0.2673 )); //17 - 18
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 19);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 4.2634 )); //18 - 19
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 20);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 0.9411 )); //19 - 20
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 21);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 2.3666 )); //20 - 21
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 22);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 1.8853 )); //21 - 22
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.HOUR_OF_DAY, 23);
		 * endDate = calendar.getTime(); usagesRepository.save(new Usages( 2L,
		 * startDate, endDate, 1.3618 )); //22 - 23
		 * 
		 * startDate = calendar.getTime(); calendar.set(Calendar.DAY_OF_MONTH, 19);
		 * calendar.set(Calendar.HOUR_OF_DAY, 0); endDate = calendar.getTime();
		 * usagesRepository.save(new Usages( 2L, startDate, endDate, 3.7998 )); //23 - 0
		 * 
		 * usagesRepository.findAll().forEach(usages -> log.info("Preloaded " +
		 * usages));
		 */
    };
  }
}