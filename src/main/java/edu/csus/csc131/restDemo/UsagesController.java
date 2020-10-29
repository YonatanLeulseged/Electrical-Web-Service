package edu.csus.csc131.restDemo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsagesController {

  private final UsagesRepository repository;
  private final UsagesModelAssembler assembler;
  

  UsagesController(UsagesRepository repository, UsagesModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }
  
  // Aggregate root
  //Get all
  @GetMapping("/usages")
  CollectionModel<EntityModel<Usages>> all(@RequestParam(required = false) Long userId,
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
	 
		List<EntityModel<Usages>> usages = usagesList
				.stream()
	  	.map(assembler::toModel) //
	  	.collect(Collectors.toList());
	 
	 return CollectionModel.of(usages, linkTo(methodOn(UsagesController.class).all(null, null, null, null)).withRel("usages"));
	 
 }
  
  //Get Single User's usages
  @GetMapping("/usages/{id}")
  EntityModel<Usages> one(@PathVariable Long id) {

		Usages usageList = repository.findById(
				id)
        .orElseThrow(() -> new UsagesNotFoundException(id));

    return assembler.toModel(usageList);
  }
    
  //Add usage
  @PostMapping("/usages")
  ResponseEntity<?> newUsages(@RequestBody Usages newUsages) {

    EntityModel<Usages> entityModel = assembler.toModel(repository.save(newUsages));

    return ResponseEntity //
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
					.toUri())
        .body(entityModel);
  }
  
  //Update Usage
  @PutMapping("/usages/{id}")
  ResponseEntity<?> replaceUsages(@RequestBody Usages newUsages, @PathVariable Long id) {

		Usages updatedUsages = repository.findById(
				id)
        .map( usages -> {
        	usages.setUserId(newUsages.getUserId());
        	usages.setStartTime(newUsages.getStartTime());
        	usages.setEndTime(newUsages.getEndTime());
        	usages.setValue(newUsages.getValue());
          return repository.save(usages);
        }) //
        .orElseGet(() -> {
          newUsages.setId(id);
          return repository.save(newUsages);
        });

    EntityModel<Usages> entityModel = assembler.toModel(updatedUsages);

	return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/usages/{id}")
  ResponseEntity<?> deleteUsages(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
}
